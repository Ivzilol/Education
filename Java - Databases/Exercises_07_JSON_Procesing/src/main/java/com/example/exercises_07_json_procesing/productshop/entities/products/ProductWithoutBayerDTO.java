package com.example.exercises_07_json_procesing.productshop.entities.products;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public class ProductWithoutBayerDTO {

    private String name;
    private BigDecimal price;
    private String seller;

    public ProductWithoutBayerDTO(String name, BigDecimal price, String firstName, String lastName) {
        this.name = name;
        this.price = price;
        if (firstName == null){
            this.seller = lastName;
        }else {
            this.seller = firstName + ' ' + lastName;
        }

    }

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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
