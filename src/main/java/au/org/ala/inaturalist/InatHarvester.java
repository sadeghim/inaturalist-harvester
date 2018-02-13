package au.org.ala.inaturalist;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.apache.commons.cli.*;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class InatHarvester {

	private static final Logger log = LoggerFactory.getLogger(InatHarvester.class);

	private static final String DEFAULT_CONFIG_FILE = "./config.properties";
	private static final int DEFAULT_THREADS_COUNT = 5;
	private static final String DATA_RESOURCE_ID = "dr1411" ;
	private final MongoDatabase database;
	private final ExecutorService inatES;
	private MongodExecutable mongodExecutable;
    private final AtomicInteger pageIndex =  new AtomicInteger(1);
    private final AtomicInteger lastId =  new AtomicInteger(0);
	private final Config conf;
	private final MongoClient mongoClient;
	private static final int DEFAULT_TIMEOUT = 1200 * 1000;


	public InatHarvester(String configFilePath) throws ConfigurationException, IOException {
		conf = new Config(configFilePath);
		inatES = Executors.newFixedThreadPool(DEFAULT_THREADS_COUNT);

		String bindIp = "localhost";
		int port = 25252;
		startMongo(bindIp, port);
		mongoClient = new MongoClient( bindIp , port );
		// create codec registry for POJOs
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		this.database = mongoClient.getDatabase("ala").withCodecRegistry(pojoCodecRegistry);
	}

	private void start() throws IOException {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

		Object sentinel = new Object();

		cm.setMaxTotal(10); // set max number of connections
		cm.setDefaultMaxPerRoute(10);

		RequestConfig config = RequestConfig.custom().setConnectTimeout(DEFAULT_TIMEOUT)
				.setConnectionRequestTimeout(DEFAULT_TIMEOUT).setSocketTimeout(DEFAULT_TIMEOUT).build();

		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(cm)
				.setDefaultRequestConfig(config)
				.build();

		InaturalistReader inatReader = new InaturalistReader(httpClient, conf.API_BASE_URL, pageIndex, database, conf.PAGE_SIZE, lastId);

		inatReader.authenticate(conf.APP_ID, conf.APP_SECRET, conf.GRANT_TYPE, conf.USERNAME, conf.PASSWORD);
		try {
			inatReader.getPageCount(conf.PAGE_SIZE);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < conf.THREAD_COUNT; i++){
			inatES.execute(inatReader);
			inatReader = new InaturalistReader(httpClient, conf.API_BASE_URL, pageIndex, database, conf.PAGE_SIZE, lastId);
		}

		inatES.shutdown();
		int readerExecutorWait = 0;
		try {
			while (!inatES.awaitTermination(1, TimeUnit.MINUTES) && !Thread.currentThread().isInterrupted()
                    && readerExecutorWait < 300) { // 5 hours
                readerExecutorWait++;
                log.warn("Reader threads not complete after {} minutes, waiting for them again", readerExecutorWait);
            }
			exportCsv("dr1411-eol", "images.csv", conf.EOL_FIELDS);
			exportCsv("dr1411-dwc", "observations.csv", conf.EOL_FIELDS);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mongodExecutable.stop();
		}

	}

	private void exportCsv(String collection, String filename, String fields) throws IOException, InterruptedException {
		String query = "mongoexport --host " + mongoClient.getServerAddressList().get(0).toString() + " -d ala -c "+ collection +" --type=csv --fields " +
				fields + " -o " + conf.CSV_EXPORT_DIR + filename ;
		Process process = Runtime.getRuntime().exec(query);
		log.info("Ruuning export command:{}", query);
		if (process != null && process.waitFor() == 0 && process.exitValue() == 0) {
			log.info( collection + " exported successfully.");
		}
	}

	private void startMongo(String bindIp, int port) throws IOException {

		File dbDir = new File(conf.TEMP_DB_DIR);
		if(dbDir.exists())
			FileUtils.deleteDirectory(dbDir);

		MongodStarter starter = MongodStarter.getDefaultInstance();

		IMongodConfig mongodConfig = new MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.replication(new Storage(conf.TEMP_DB_DIR,null,0))
				.net(new Net(bindIp, port, Network.localhostIsIPv6()))
				.build();

		mongodExecutable = starter.prepare(mongodConfig);
		MongodProcess mongod = mongodExecutable.start();

	}

	public static void main(String... args) throws Exception {

		final CommandLineParser parser = new DefaultParser();

		final Options options = new Options();
		options.addOption("h", "help", false, "prints this message.");
		options.addOption("c", "config", true, "Configuration file path. default is:" + DEFAULT_CONFIG_FILE);

		try {
			// parse the command line arguments
			final CommandLine line = parser.parse(options, args);

			if (line.hasOption("help")) {
				final HelpFormatter helpFormatter = new HelpFormatter();
				helpFormatter.printHelp("InatHarvester", options);
				return;
			}

			final String cfg = line.getOptionValue("config", DEFAULT_CONFIG_FILE);

			InatHarvester harvester = null;

			harvester = new InatHarvester(cfg);
			harvester.start();

		} catch (ParseException exp) {
			System.out.println("Unexpected exception: " + exp.getMessage());
			final HelpFormatter helpFormatter = new HelpFormatter();
			helpFormatter.printHelp("InatHarvester", options);
			throw exp;
		}
	}


}
