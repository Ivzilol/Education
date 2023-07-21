package com.example.exercises_07_json_procesing.productshop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
@XmlRootElement(name = "category")
public class CategoryStatsDTO {
    @XmlElement(name = "name")
    private String category;
    @XmlElement(name = "product-count")
    private long productCount;
    @XmlElement(name = "averagePrice")
    private double averagePrice;
    @XmlElement(name = "totalRevenue")
    private BigDecimal totalRevenue;

    public CategoryStatsDTO(String category, long productCount, double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productCount = productCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }


    public String getCategory() {
        return category;
    }

    public long getProductCount() {
        return productCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
