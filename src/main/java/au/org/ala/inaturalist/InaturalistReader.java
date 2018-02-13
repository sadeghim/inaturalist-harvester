package au.org.ala.inaturalist;

import au.org.ala.inaturalist.domain.DataObject;
import au.org.ala.inaturalist.domain.SimpleDarwinRecord;
import au.org.ala.inaturalist.domain.SimpleDarwinRecordSet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InaturalistReader implements Runnable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CloseableHttpClient httpClient;
    private final HttpContext context;
    private final String oauthUrl;
    private final String observationsJsonUrl;
    private final String observationsDwcUrl;
    private final ObjectMapper objectMapper;
    private final MongoDatabase database;
    public static int pageCount;
    private static String token = "";
    private final int pageSize;
    private final AtomicInteger pageIndex;
    private final AtomicInteger lastId;

    public InaturalistReader(CloseableHttpClient httpClient, String baseUrl, AtomicInteger pageIndex, MongoDatabase database, int pageSize, AtomicInteger lastId) {
        this.httpClient = httpClient;
        this.oauthUrl = baseUrl + "/oauth/token";
        this.observationsJsonUrl = baseUrl + "/observations.json";
        this.observationsDwcUrl = baseUrl + "/observations.dwc";
        this.pageIndex = pageIndex;
        this.lastId = lastId;
        this.context = HttpClientContext.create();
        this.objectMapper = new ObjectMapper();
        this.database = database;
        this.pageSize = pageSize;
    }

    public boolean authenticate(String clientId, String clientSecret, String grantType, String username, String password) throws IOException {

        HttpPost httpPost = new HttpPost(oauthUrl);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("client_id", clientId));
        nvps.add(new BasicNameValuePair("client_secret", clientSecret));
        nvps.add(new BasicNameValuePair("username", username));
        nvps.add(new BasicNameValuePair("password", password));
        nvps.add(new BasicNameValuePair("grant_type", grantType));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });

        token = (String) map.get("access_token");
        log.info("Authentication token is : {}", token);

        return (token != null);

    }

    private CloseableHttpResponse fetchUrl(String url, List<NameValuePair> params) throws IOException, URISyntaxException {
        URI uri = (params != null) ? new URIBuilder(url).setParameters(params).build() : new URIBuilder(url).build();
        HttpGet request = new HttpGet();
        request.setHeader("Authorization", "Bearer " + token);
        request.setHeader("Content-Type", "application/json");
        request.setURI(uri);
        log.info("Fetching url: {}", uri.toString());
        CloseableHttpResponse response = httpClient.execute(request);

        return response;
    }


    public int getPageCount(int pageSize) throws IOException, URISyntaxException {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("place_id", "6744"));
        CloseableHttpResponse response = fetchUrl(observationsJsonUrl, nvps);
        pageCount = Integer.parseInt(response.getFirstHeader("X-Total-Entries").getValue()) / pageSize;

        log.info("X-Total-Entries: {}", response.getFirstHeader("X-Total-Entries").getValue());
        log.info("there will be {} page calls each having {} records.", pageCount, pageSize);
        return pageCount;
    }

    private void readRecords(int pageNumber) throws IOException, URISyntaxException, InterruptedException, JAXBException {

        List<NameValuePair> params = new ArrayList<>();
        params.add( 0,new BasicNameValuePair("page", pageNumber+""));
        params.add( 1,new BasicNameValuePair("max", pageSize+""));
        params.add( 2,new BasicNameValuePair("place_id", "6744"));

        CloseableHttpResponse response = fetchUrl(observationsDwcUrl, params);

        JAXBContext jaxbContext = JAXBContext.newInstance(SimpleDarwinRecordSet.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        SimpleDarwinRecordSet darwinRecordSet = (SimpleDarwinRecordSet) jaxbUnmarshaller.unmarshal( response.getEntity().getContent() );
        response.close();

        final ObjectMapper mapper = new ObjectMapper();

        MongoCollection drCollection = database.getCollection("dr1411-dwc", SimpleDarwinRecord.class);
        MongoCollection eolCollection = database.getCollection("dr1411-eol", DataObject.class);
        if (darwinRecordSet.getSimpleDarwinRecordSet().size() > 0) {
            for (SimpleDarwinRecord record : darwinRecordSet.getSimpleDarwinRecordSet()) {
                DBObject drDbObject = BasicDBObject.parse(mapper.writeValueAsString(record));
                drCollection.insertOne(drDbObject);
                if (record.getDataObjectList() != null) {
                    for (DataObject dataObject : record.getDataObjectList()) {
                        dataObject.setId(record.getId());
//                        log.info("dataObject.getIdentifier(): {}", dataObject.getIdentifier());
                        DBObject eolDbObject = BasicDBObject.parse(mapper.writeValueAsString(dataObject));
                        eolCollection.insertOne(eolDbObject);
                    }
                }
            }
        }
    }


    @Override
    public void run() {
        int retry = 1;
        while (true) {
            try {
                int pageNumber = pageIndex.getAndIncrement();
                if ( pageNumber >= pageCount) {
                    log.info("Quitting the thread as the pageCount is zero.");
                    break;
                }
                readRecords(pageNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }


}
