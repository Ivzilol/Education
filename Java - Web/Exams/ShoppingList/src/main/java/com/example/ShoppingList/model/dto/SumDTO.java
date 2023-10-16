package com.example.ShoppingList.model.dto;

import java.math.BigDecimal;

public class SumDTO {

    private BigDecimal sum;

    public SumDTO(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
