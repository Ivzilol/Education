package com.example.spotifyplaylistapp.model.entity.enums;

public enum StyleName {

    POP ("Pop"),
    ROCK ("Rock"),
    JAZZ ("Jazz");


    private final String value;

    private StyleName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
