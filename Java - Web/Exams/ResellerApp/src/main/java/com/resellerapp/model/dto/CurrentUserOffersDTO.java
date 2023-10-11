package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Condition;

import java.math.BigDecimal;

public class CurrentUserOffersDTO {

    private Long id;

    private Condition condition;

    private BigDecimal price;

    private String description;

    public CurrentUserOffersDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
