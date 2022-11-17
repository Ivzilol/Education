package com.example.exercises_07_json_procesing.productshop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLCategoryStatsDTO implements Serializable {
    @XmlElement(name = "name")
    private String category;

    private long productCount;

    private double averagePrice;

    private BigDecimal totalRevenue;

    public XMLCategoryStatsDTO() {
    }

    public XMLCategoryStatsDTO(CategoryStatsDTO other) {
        this.category = other.getCategory();
        this.productCount = other.getProductCount();
        this.averagePrice = other.getAveragePrice();
        this.totalRevenue = other.getTotalRevenue();
    }
}
