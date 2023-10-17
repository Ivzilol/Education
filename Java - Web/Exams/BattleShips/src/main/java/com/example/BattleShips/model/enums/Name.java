package com.example.BattleShips.model.enums;

public enum Name {

    BATTLE ("Battle"),
    CARGO ("Cargo"),
    PATROL ("Patrol");

    private final String value;

    Name(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
