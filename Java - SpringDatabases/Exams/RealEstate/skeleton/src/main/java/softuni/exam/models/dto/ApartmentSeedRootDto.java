package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "apartments")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentSeedRootDto {
    @XmlElement(name = "apartment")
    private List<ApartmentSeedDto> apartmentSeedDtoList;

    public List<ApartmentSeedDto> getApartmentSeedDtoList() {
        return apartmentSeedDtoList;
    }

    public void setApartmentSeedDtoList(List<ApartmentSeedDto> apartmentSeedDtoList) {
        this.apartmentSeedDtoList = apartmentSeedDtoList;
    }
}
