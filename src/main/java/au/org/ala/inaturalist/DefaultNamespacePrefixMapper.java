package au.org.ala.inaturalist;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link NamespacePrefixMapper} that maps the schema
 * namespaces more to readable names. Used by the jaxb marshaller. Requires
 * setting the property "com.sun.xml.bind.namespacePrefixMapper" to an instance
 * of this class.
 * <p>
 * Requires dependency on JAXB implementation jars
 * </p>
 */
public class DefaultNamespacePrefixMapper extends NamespacePrefixMapper {

    private Map<String, String> namespaceMap = new HashMap<>();

    /**
     * Create mappings.
     */
    public DefaultNamespacePrefixMapper() {
        namespaceMap.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        namespaceMap.put("http://rs.tdwg.org/ac/terms/", "ac");
        namespaceMap.put("http://purl.org/dc/terms/", "dcterms");
        namespaceMap.put("http://rs.tdwg.org/dwc/terms/", "dwc");
        namespaceMap.put("http://rs.tdwg.org/dwc/xsd/simpledarwincore/", "dwr");
        namespaceMap.put("http://www.eol.org/transfer/content/1.0", "eol");
        namespaceMap.put("http://www.w3.org/2003/01/geo/wgs84_pos#", "geo");
        namespaceMap.put("http://eol.org/schema/media/", "media");
        namespaceMap.put("http://eol.org/schema/reference/", "ref");
        namespaceMap.put("http://ns.adobe.com/xap/1.0/", "zap");

    }

    /* (non-Javadoc)
     * Returning null when not found based on spec.
     * @see com.sun.xml.bind.marshaller.NamespacePrefixMapper#getPreferredPrefix(java.lang.String, java.lang.String, boolean)
     */
    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        return namespaceMap.getOrDefault(namespaceUri, suggestion);
    }
}