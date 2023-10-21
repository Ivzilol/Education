package com.dictionaryapp.model.dto;


import java.time.LocalDate;

public class SpanishWordsDTO extends GermanWordsDTO{
    public SpanishWordsDTO(Long id, String term, String translation, String example, String username, LocalDate inputDate) {
        super(id, term, translation, example, username, inputDate);
    }
}
