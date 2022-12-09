package softuni.exam.models.dto;

import org.springframework.data.annotation.AccessType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "apartments")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportApartmentRootDTO {

    @XmlElement(name = "apartment")
    List<ImportApartmentDTO> apartments;

    public List<ImportApartmentDTO> getApartments() {
        return apartments;
    }
}
