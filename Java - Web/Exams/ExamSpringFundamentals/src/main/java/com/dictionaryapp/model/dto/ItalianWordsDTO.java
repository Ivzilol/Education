package com.dictionaryapp.model.dto;

import java.time.LocalDate;

public class ItalianWordsDTO extends GermanWordsDTO{
    public ItalianWordsDTO(Long id, String term, String translation, String example, String username, LocalDate inputDate) {
        super(id, term, translation, example, username, inputDate);
    }
}
