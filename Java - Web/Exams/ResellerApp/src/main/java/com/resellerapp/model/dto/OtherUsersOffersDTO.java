package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Condition;

import java.math.BigDecimal;

public class OtherUsersOffersDTO {

    private Long id;

    private String username;

    private String description;

    private Condition condition;

    private BigDecimal price;

    public OtherUsersOffersDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
