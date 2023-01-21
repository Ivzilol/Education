package exam.model.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportShopRootDto {

    @XmlElement(name = "shop")
    private List<ImportShopsDTO> shops;

    public List<ImportShopsDTO> getShops() {
        return shops;
    }
}
