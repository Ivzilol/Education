package com.dictionaryapp.model.enums;

public enum LanguageName {

    GERMAN ("German"),
    SPANISH ("Spanish"),
    FRENCH ("French"),
    ITALIAN ("Italian");

    private String value;

    LanguageName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
