package com.example.ShoppingList.model.enums;

public enum Name {

    FOOD ("Food"),
    DRINK ("Drink"),
    HOUSEHOLD ("Household"),
    OTHER ("Other");

    private String value;

    Name(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
