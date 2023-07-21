package exam.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopSeedRootDto {

    @XmlElement(name = "shop")
    private List<ShopSeedDto> shopSeedDtoList;


    public List<ShopSeedDto> getShopSeedDtoList() {
        return shopSeedDtoList;
    }

    public void setShopSeedDtoList(List<ShopSeedDto> shopSeedDtoList) {
        this.shopSeedDtoList = shopSeedDtoList;
    }
}
