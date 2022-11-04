package com.example.exercises_08_xml.productshop.entities.products;

import java.math.BigDecimal;

public class ExportNamePriceProductDTO {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
