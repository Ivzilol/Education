package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleName;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleServiceImpl implements StyleService{

    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void initStyle() {
        if (this.styleRepository.count() != 0) {
            return;
        }
        Arrays.stream(StyleName.values())
                .forEach(s -> {
                    Style style = new Style();
                    style.setStyleName(s);
                    style.setDescription("...");
                    styleRepository.save(style);
                });
    }

    @Override
    public Style findStyle(StyleName style) {
        return this.styleRepository.findByStyleName(style).orElseThrow(null);
    }
}
