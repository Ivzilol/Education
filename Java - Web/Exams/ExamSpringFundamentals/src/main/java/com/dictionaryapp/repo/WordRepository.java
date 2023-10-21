package com.dictionaryapp.repo;

import com.dictionaryapp.model.dto.FrenchWordsDTO;
import com.dictionaryapp.model.dto.GermanWordsDTO;
import com.dictionaryapp.model.dto.ItalianWordsDTO;
import com.dictionaryapp.model.dto.SpanishWordsDTO;
import com.dictionaryapp.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    @Query("select new com.dictionaryapp.model.dto.GermanWordsDTO(" +
            "w.id ,w.term, w.translation, w.example, u.username, w.inputDate)" +
            " from Word as w" +
            " join User as u on w.addedBy.id = u.id" +
            " join Language as l on w.language.id = l.id" +
            " where l.languageName = 'German'")
    Set<GermanWordsDTO> findGermanWords();
    @Query("select new com.dictionaryapp.model.dto.SpanishWordsDTO(" +
            "w.id ,w.term, w.translation, w.example, u.username, w.inputDate)" +
            " from Word as w" +
            " join User as u on w.addedBy.id = u.id" +
            " join Language as l on w.language.id = l.id" +
            " where l.languageName = 'Spanish'")
    Set<SpanishWordsDTO> findSpanishWords();
    @Query("select new com.dictionaryapp.model.dto.FrenchWordsDTO(" +
            "w.id ,w.term, w.translation, w.example, u.username, w.inputDate)" +
            " from Word as w" +
            " join User as u on w.addedBy.id = u.id" +
            " join Language as l on w.language.id = l.id" +
            " where l.languageName = 'French'")
    Set<FrenchWordsDTO> findFrenchWords();

    @Query("select new com.dictionaryapp.model.dto.ItalianWordsDTO(" +
            "w.id ,w.term, w.translation, w.example, u.username, w.inputDate)" +
            " from Word as w" +
            " join User as u on w.addedBy.id = u.id" +
            " join Language as l on w.language.id = l.id" +
            " where l.languageName = 'Italian'")
    Set<ItalianWordsDTO> findItalianWords();
}
