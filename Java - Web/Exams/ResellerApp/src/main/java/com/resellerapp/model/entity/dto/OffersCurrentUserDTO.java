package com.resellerapp.model.entity.dto;


import com.resellerapp.model.entity.enums.ConditionName;

import java.math.BigDecimal;

public class OffersCurrentUserDTO {

    private Long id;

    private Enum<ConditionName> conditionName;

    private BigDecimal price;

    private String description;

    public OffersCurrentUserDTO(Long id, Enum<ConditionName> conditionName, BigDecimal price, String description) {
        this.id = id;
        this.conditionName = conditionName;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
