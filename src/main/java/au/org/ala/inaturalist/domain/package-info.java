@XmlSchema(
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns={
                @XmlNs(prefix="xsi",namespaceURI="http://www.w3.org/2001/XMLSchema-instance"),
                @XmlNs(prefix= "ac", namespaceURI="http://rs.tdwg.org/ac/terms/"),
                @XmlNs(prefix= "dcterms", namespaceURI="http://purl.org/dc/terms/"),
                @XmlNs(prefix = "dwc", namespaceURI="http://rs.tdwg.org/dwc/terms/" ),
                @XmlNs(prefix = "dwr", namespaceURI = "http://rs.tdwg.org/dwc/xsd/simpledarwincore/" ),
                @XmlNs(prefix = "eol", namespaceURI = "http://www.eol.org/transfer/content/1.0" ),
                @XmlNs(prefix = "geo", namespaceURI = "http://www.w3.org/2003/01/geo/wgs84_pos#" ),
                @XmlNs(prefix = "media", namespaceURI = "http://eol.org/schema/media/" ),
                @XmlNs(prefix = "ref", namespaceURI = "http://eol.org/schema/reference/" ),
                @XmlNs(prefix = "xap", namespaceURI = "http://ns.adobe.com/xap/1.0/" )

        }
)
package au.org.ala.inaturalist.domain;

import javax.xml.bind.annotation.*;