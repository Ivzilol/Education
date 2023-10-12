package com.likebookapp.model.enums;

public enum MoodName {

    HAPPY ("Happy"),
    SAD ("Sad"),
    INSPIRED ("Inspired");

    private final String value;

    MoodName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
