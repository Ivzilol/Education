package com.example.ShoppingList.model.dto;

import java.math.BigDecimal;

public class HouseholdsDTO extends FoodDTO{
    public HouseholdsDTO(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }
}
