package com.example.exercises_07_json_procesing.productshop.entities.products;

import java.math.BigDecimal;

public class SoldProductDTO {
    private String name;
    private BigDecimal price;
    private String bayerFirstName;
    private String bayerLastName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBayerFirstName() {
        return bayerFirstName;
    }

    public void setBayerFirstName(String bayerFirstName) {
        this.bayerFirstName = bayerFirstName;
    }

    public String getBayerLastName() {
        return bayerLastName;
    }

    public void setBayerLastName(String bayerLastName) {
        this.bayerLastName = bayerLastName;
    }
}
