package com.example.exercises_08_xml.productshop.entities.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWithAttributesDTO {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private BigDecimal price;
    @XmlAttribute
    private String seller;

    public ProductWithAttributesDTO() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
