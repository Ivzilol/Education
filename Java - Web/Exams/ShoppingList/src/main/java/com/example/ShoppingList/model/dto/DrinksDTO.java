package com.example.ShoppingList.model.dto;

import java.math.BigDecimal;

public class DrinksDTO extends FoodDTO{

    public DrinksDTO(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }
}
