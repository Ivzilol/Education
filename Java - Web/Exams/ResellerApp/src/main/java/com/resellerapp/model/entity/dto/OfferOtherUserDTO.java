package com.resellerapp.model.entity.dto;

import com.resellerapp.model.entity.enums.ConditionName;

import java.math.BigDecimal;

public class OfferOtherUserDTO {

    private Long id;

    private String username;

    private String description;

    private Enum<ConditionName> conditionName;

    private BigDecimal price;

    public OfferOtherUserDTO(Long id, String username, String description, Enum<ConditionName> conditionName, BigDecimal price) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.conditionName = conditionName;
        this.price = price;
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

    public Enum<ConditionName> getConditionName() {
        return conditionName;
    }

    public void setConditionName(Enum<ConditionName> conditionName) {
        this.conditionName = conditionName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
