package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.service.StyleServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StyleInit implements CommandLineRunner {

    private final StyleServiceImpl styleService;

    public StyleInit(StyleServiceImpl styleService) {
        this.styleService = styleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.styleService.initStyle();
    }
}
