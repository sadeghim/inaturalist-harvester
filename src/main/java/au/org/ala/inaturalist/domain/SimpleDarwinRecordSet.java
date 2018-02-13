package au.org.ala.inaturalist.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "SimpleDarwinRecordSet" , namespace = "http://rs.tdwg.org/dwc/xsd/simpledarwincore/")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleDarwinRecordSet {

    @XmlElement( name = "SimpleDarwinRecord",  namespace = "http://rs.tdwg.org/dwc/xsd/simpledarwincore/")
    List<SimpleDarwinRecord> simpleDarwinRecordSet;

    public List<SimpleDarwinRecord> getSimpleDarwinRecordSet() {
        return simpleDarwinRecordSet;
    }

    public void setSimpleDarwinRecordSet(List simpleDarwinRecordSet) {
        this.simpleDarwinRecordSet = simpleDarwinRecordSet;
    }
}
