package softuni.exam.models.dto;

import softuni.exam.models.entity.Town;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportApartmentDTO {

    @XmlElement
    private String apartmentType;

    @XmlElement
    @DecimalMin(value = "40.00")
    private Double area;

    @XmlElement
    private String town;


    public String getApartmentType() {
        return apartmentType;
    }

    public Double getArea() {
        return area;
    }

    public String    getTown() {
        return town;
    }
}
