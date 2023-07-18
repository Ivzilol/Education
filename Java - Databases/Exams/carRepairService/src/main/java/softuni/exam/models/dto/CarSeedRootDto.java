package softuni.exam.models.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedRootDto {

    @XmlElement(name = "car")
    private List<CarSeedDto> carSeedDtoList;

    public List<CarSeedDto> getCarSeedDtoList() {
        return carSeedDtoList;
    }

    public void setCarSeedDtoList(List<CarSeedDto> carSeedDtoList) {
        this.carSeedDtoList = carSeedDtoList;
    }
}
