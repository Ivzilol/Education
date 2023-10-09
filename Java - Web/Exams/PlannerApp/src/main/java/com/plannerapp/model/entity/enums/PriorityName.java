package com.plannerapp.model.entity.enums;

public enum PriorityName {

    URGENT("Urgent"),
    IMPORTANT("Important"),
    LOW("Low");

    private final String value;

    private PriorityName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
