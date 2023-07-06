package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerSeedRootDto {
    @XmlElement(name = "seller")
    private List<SellerSeedDto> sellerSeedDtoList;

    public List<SellerSeedDto> getSellerSeedDtoList() {
        return sellerSeedDtoList;
    }

    public void setSellerSeedDtoList(List<SellerSeedDto> sellerSeedDtoList) {
        this.sellerSeedDtoList = sellerSeedDtoList;
    }
}
