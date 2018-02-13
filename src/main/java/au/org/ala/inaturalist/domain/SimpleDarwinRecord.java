package au.org.ala.inaturalist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "SimpleDarwinRecord" , namespace ="http://rs.tdwg.org/dwc/xsd/simpledarwincore/")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleDarwinRecord {

    @XmlElement( name = "catalogNumber", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String id;

    @JsonIgnore
    @XmlElement( name = "dataObject"  , namespace = "http://www.eol.org/transfer/content/1.0")
    List<DataObject> dataObjectList;
    @XmlElement( name = "occurrenceID"  , namespace = "http://rs.tdwg.org/dwc/terms/")
    private String occurrenceID;
    @XmlElement( name = "basisOfRecord"  , namespace = "http://rs.tdwg.org/dwc/terms/")
    private String basisOfRecord;
    @XmlElement( name = "modified", namespace = "http://purl.org/dc/terms/")
    private String modified;
    @XmlElement( name = "institutionCode", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String institutionCode;
    @XmlElement( name = "collectionCode", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String collectionCode;
    @XmlElement( name = "datasetName", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String datasetName;
    @XmlElement( name = "catalogNumber", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String catalogNumber;
    @XmlElement( name = "references", namespace = "http://purl.org/dc/terms/")
    private String references;
    @XmlElement( name = "occurrenceDetails", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String occurrenceDetails;
    @XmlElement( name = "recordedBy", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String recordedBy;
    @XmlElement( name = "establishmentMeans", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String establishmentMeans;
    @XmlElement( name = "eventDate", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String eventDate;
    @XmlElement( name = "eventTime", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String eventTime;
    @XmlElement( name = "verbatimEventDate", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String verbatimEventDate;
    @XmlElement( name = "verbatimLocality", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String verbatimLocality;
    @XmlElement( name = "decimalLatitude", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String decimalLatitude;
    @XmlElement( name = "decimalLongitude", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String decimalLongitude;
    @XmlElement( name = "coordinateUncertaintyInMeters", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String coordinateUncertaintyInMeters;
    @XmlElement( name = "countryCode", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String countryCode;
    @XmlElement( name = "identificationID", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String identificationID;
    @XmlElement( name = "dateIdentified", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String dateIdentified;
    @XmlElement( name = "taxonID", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String taxonID;
    @XmlElement( name = "scientificName", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String scientificName;
    @XmlElement( name = "taxonRank", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String taxonRank;
    @XmlElement( name = "kingdom", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String kingdom;
    @XmlElement( name = "phylum", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String phylum;
    @XmlElement( name = "class", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String classs;
    @XmlElement( name = "order", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String order;
    @XmlElement( name = "family", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String family;
    @XmlElement( name = "genus", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String genus;
    @XmlElement( name = "license", namespace = "http://purl.org/dc/terms/")
    private String license;
    @XmlElement( name = "rights", namespace = "http://purl.org/dc/terms/")
    private String rights;
    @XmlElement( name = "rightsHolder", namespace = "http://purl.org/dc/terms/")
    private String rightsHolder;
    @XmlElement( name = "occurrenceRemarks", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String occurrenceRemarks;
    @XmlElement( name = "informationWithheld", namespace = "http://rs.tdwg.org/dwc/terms/")
    private String informationWithheld;


    public String getOccurrenceID() {
        return occurrenceID;
    }
    public void setOccurrenceID(String occurrenceID) {
        this.occurrenceID = occurrenceID;
    }
    public String getBasisOfRecord() {
        return basisOfRecord;
    }
    public void setBasisOfRecord(String basisOfRecord) {
        this.basisOfRecord = basisOfRecord;
    }
    public String getModified() {
        return modified;
    }
    public void setModified(String modified) {
        this.modified = modified;
    }
    public String getInstitutionCode() {
        return institutionCode;
    }
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }
    public String getCollectionCode() {
        return collectionCode;
    }
    public void setCollectionCode(String collectionCode) {
        this.collectionCode = collectionCode;
    }
    public String getDatasetName() {
        return datasetName;
    }
    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }
    public String getCatalogNumber() {
        return catalogNumber;
    }
    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }
    public String getReferences() {
        return references;
    }
    public void setReferences(String references) {
        this.references = references;
    }
    public String getOccurrenceDetails() {
        return occurrenceDetails;
    }
    public void setOccurrenceDetails(String occurrenceDetails) {
        this.occurrenceDetails = occurrenceDetails;
    }
    public String getRecordedBy() {
        return recordedBy;
    }
    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }
    public String getEstablishmentMeans() {
        return establishmentMeans;
    }
    public void setEstablishmentMeans(String establishmentMeans) {
        this.establishmentMeans = establishmentMeans;
    }
    public String getEventDate() {
        return eventDate;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    public String getEventTime() {
        return eventTime;
    }
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
    public String getVerbatimEventDate() {
        return verbatimEventDate;
    }
    public void setVerbatimEventDate(String verbatimEventDate) {
        this.verbatimEventDate = verbatimEventDate;
    }
    public String getVerbatimLocality() {
        return verbatimLocality;
    }
    public void setVerbatimLocality(String verbatimLocality) {
        this.verbatimLocality = verbatimLocality;
    }
    public String getDecimalLatitude() {
        return decimalLatitude;
    }
    public void setDecimalLatitude(String decimalLatitude) {
        this.decimalLatitude = decimalLatitude;
    }
    public String getDecimalLongitude() {
        return decimalLongitude;
    }
    public void setDecimalLongitude(String decimalLongitude) {
        this.decimalLongitude = decimalLongitude;
    }
    public String getCoordinateUncertaintyInMeters() {
        return coordinateUncertaintyInMeters;
    }
    public void setCoordinateUncertaintyInMeters(String coordinateUncertaintyInMeters) {
        this.coordinateUncertaintyInMeters = coordinateUncertaintyInMeters;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getIdentificationID() {
        return identificationID;
    }
    public void setIdentificationID(String identificationID) {
        this.identificationID = identificationID;
    }
    public String getDateIdentified() {
        return dateIdentified;
    }
    public void setDateIdentified(String dateIdentified) {
        this.dateIdentified = dateIdentified;
    }
    public String getTaxonID() {
        return taxonID;
    }
    public void setTaxonID(String taxonID) {
        this.taxonID = taxonID;
    }
    public String getScientificName() {
        return scientificName;
    }
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }
    public String getTaxonRank() {
        return taxonRank;
    }
    public void setTaxonRank(String taxonRank) {
        this.taxonRank = taxonRank;
    }
    public String getKingdom() {
        return kingdom;
    }
    public void setKingdom(String kingdom) {
        this.kingdom = kingdom;
    }
    public String getPhylum() {
        return phylum;
    }
    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }
    public String getClasss() {
        return classs;
    }
    public void setClasss(String classs) {
        this.classs = classs;
    }
    public String getOrder() {
        return order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public String getFamily() {
        return family;
    }
    public void setFamily(String family) {
        this.family = family;
    }
    public String getGenus() {
        return genus;
    }
    public void setGenus(String genus) {
        this.genus = genus;
    }
    public String getLicense() {
        return license;
    }
    public void setLicense(String license) {
        this.license = license;
    }
    public String getRights() {
        return rights;
    }
    public void setRights(String rights) {
        this.rights = rights;
    }
    public String getRightsHolder() {
        return rightsHolder;
    }
    public void setRightsHolder(String rightsHolder) {
        this.rightsHolder = rightsHolder;
    }
    public String getOccurrenceRemarks() {
        return occurrenceRemarks;
    }
    public void setOccurrenceRemarks(String occurrenceRemarks) {
        this.occurrenceRemarks = occurrenceRemarks;
    }
    public String getInformationWithheld() {
        return informationWithheld;
    }
    public void setInformationWithheld(String informationWithheld) {
        this.informationWithheld = informationWithheld;
    }
    public List<DataObject> getDataObjectList() {
        return dataObjectList;
    }
    public void setDataObjectList(List<DataObject> dataObjectList) {
        this.dataObjectList = dataObjectList;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
