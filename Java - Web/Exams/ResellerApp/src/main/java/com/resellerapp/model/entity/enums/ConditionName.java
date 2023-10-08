package com.resellerapp.model.entity.enums;

public enum ConditionName {

    EXCELLENT("Excellent"),
    GOOD("Good"),
    ACCEPTABLE("Acceptable");

    private final String value;

    private ConditionName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
