package com.example.spotifyplaylistapp.service;


import com.example.spotifyplaylistapp.model.entity.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleName;

public interface StyleService {

    void initStyle();

    Style findStyle(StyleName style);
}
