package com.dictionaryapp.init;

import com.dictionaryapp.service.LanguageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitLanguage implements CommandLineRunner {

    private final LanguageService languageService;

    public InitLanguage(LanguageService languageService) {
        this.languageService = languageService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.languageService.initLanguage();
    }
}
