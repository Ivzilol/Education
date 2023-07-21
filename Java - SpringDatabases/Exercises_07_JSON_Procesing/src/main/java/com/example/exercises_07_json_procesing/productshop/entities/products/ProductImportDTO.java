package com.example.exercises_07_json_procesing.productshop.entities.products;

import java.math.BigDecimal;

public class ProductImportDTO {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
