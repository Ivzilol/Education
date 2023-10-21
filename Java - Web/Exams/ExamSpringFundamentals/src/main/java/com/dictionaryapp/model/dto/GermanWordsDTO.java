package com.dictionaryapp.model.dto;

import java.time.LocalDate;

public class GermanWordsDTO {

    private Long id;
    private String term;

    private String translation;

    private String example;

    private String username;

    private LocalDate inputDate;

    public GermanWordsDTO(Long id, String term, String translation, String example, String username, LocalDate inputDate) {
        this.id = id;
        this.term = term;
        this.translation = translation;
        this.example = example;
        this.username = username;
        this.inputDate = inputDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }
}
