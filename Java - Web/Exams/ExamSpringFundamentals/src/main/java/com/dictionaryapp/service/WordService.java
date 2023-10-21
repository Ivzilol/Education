package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.*;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class WordService {

    private final WordRepository wordRepository;

    private final LanguageRepository languageRepository;

    private final UserRepository userRepository;

    public WordService(WordRepository wordRepository, LanguageRepository languageRepository, UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
    }

    public void addWord(AddWordDTO addWordDTO, Long userId) {
        Word word = new Word();
        word.setTerm(addWordDTO.getTerm());
        word.setTranslation(addWordDTO.getTranslation());
        word.setExample(addWordDTO.getExample());
        word.setInputDate(addWordDTO.getInputDate());
        Language language = this.languageRepository.findLanguage(addWordDTO.getLanguage());
        word.setLanguage(language);
        Optional<User> user = this.userRepository.findById(userId);
        word.setAddedBy(user.get());
        this.wordRepository.save(word);
    }

    public Set<GermanWordsDTO> getGermanWords() {
        return this.wordRepository.findGermanWords();
    }

    public Set<SpanishWordsDTO> getSpanishWords() {
        return this.wordRepository.findSpanishWords();
    }

    public Set<FrenchWordsDTO> getFrenchWords() {
        return this.wordRepository.findFrenchWords();
    }

    public Set<ItalianWordsDTO> getItalianWords() {
        return this.wordRepository.findItalianWords();
    }

    public void removeSong(Long id) {
        this.wordRepository.deleteById(id);
    }

    public void removeAll() {
        this.wordRepository.deleteAll();
    }
}
