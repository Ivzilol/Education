package com.resellerapp.model.dto;


import com.resellerapp.model.enums.ConditionName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddOfferDTO {

    private Long id;

    @Size(min = 3, max = 20, message = "Description length must be between 2 and 20 characters")
    @NotNull
    private String description;

    @Positive(message = "The price must be a positive number")
    @NotNull
    private BigDecimal price;

    @NotNull(message = "You must select a condition")
    private ConditionName condition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ConditionName getCondition() {
        return condition;
    }

    public void setCondition(ConditionName condition) {
        this.condition = condition;
    }
}
