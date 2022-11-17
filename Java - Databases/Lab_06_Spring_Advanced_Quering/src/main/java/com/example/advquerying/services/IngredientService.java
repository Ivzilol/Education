package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> selectNameStartWith(String start);

    List<Ingredient> selectInNames(List<String> names);

    int deleteByName(String name);

    void increasePriceByPercentage(double percent);
}
