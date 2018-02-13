package au.org.ala.inaturalist.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dataObject" , namespace = "http://www.eol.org/transfer/content/1.0")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataObject {

    private String id;
    @XmlElement( name = "identifier", namespace = "http://purl.org/dc/terms/")
    private String identifier;
    @XmlElement( name = "type", namespace = "http://purl.org/dc/terms/")
    private String type;
    @XmlElement( name = "format", namespace = "http://purl.org/dc/terms/")
    private String format;
    @XmlElement( name = "accessURI", namespace = "http://rs.tdwg.org/ac/terms/")
    private String accessURI;
    @XmlElement( name = "thumbnailURL", namespace = "http://eol.org/schema/media/" )
    private String thumbnailURL;
    @XmlElement( name = "furtherInformationURL", namespace = "http://rs.tdwg.org/ac/terms/")
    private String furtherInformationURL;
    @XmlElement( name = "derivedFrom", namespace = "http://rs.tdwg.org/ac/terms/")
    private String derivedFrom;
    @XmlElement( name = "CreateDate", namespace = "http://ns.adobe.com/xap/1.0/" )
    private String createDate;
    @XmlElement( name = "modified", namespace = "http://purl.org/dc/terms/")
    private String modified;
    @XmlElement( name = "UsageTerms", namespace = "http://ns.adobe.com/xap/1.0/" )
    private String usageTerms;
    @XmlElement( name = "rights", namespace = "http://purl.org/dc/terms/")
    private String rights;
    @XmlElement( name = "Owner", namespace = "http://ns.adobe.com/xap/1.0/" )
    private String owner;
    @XmlElement( name = "publisher", namespace = "http://purl.org/dc/terms/")
    private String publisher;
    @XmlElement( name = "creator", namespace = "http://purl.org/dc/terms/")
    private String creator;
    @XmlElement( name = "description", namespace = "http://purl.org/dc/terms/")
    private String description;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAccessURI() {
        return accessURI;
    }

    public void setAccessURI(String accessURI) {
        this.accessURI = accessURI;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getFurtherInformationURL() {
        return furtherInformationURL;
    }

    public void setFurtherInformationURL(String furtherInformationURL) {
        this.furtherInformationURL = furtherInformationURL;
    }

    public String getDerivedFrom() {
        return derivedFrom;
    }

    public void setDerivedFrom(String derivedFrom) {
        this.derivedFrom = derivedFrom;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getUsageTerms() {
        return usageTerms;
    }

    public void setUsageTerms(String usageTerms) {
        this.usageTerms = usageTerms;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
