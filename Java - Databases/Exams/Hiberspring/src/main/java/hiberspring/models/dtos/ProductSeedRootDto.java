package hiberspring.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedRootDto {
    @XmlElement(name = "product")
    private List<ProductSeedDto> productSeedDtoList;

    public List<ProductSeedDto> getProductSeedDtoList() {
        return productSeedDtoList;
    }

    public void setProductSeedDtoList(List<ProductSeedDto> productSeedDtoList) {
        this.productSeedDtoList = productSeedDtoList;
    }
}
