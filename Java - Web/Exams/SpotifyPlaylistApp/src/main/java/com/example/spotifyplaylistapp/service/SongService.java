package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.dto.AddSongDTO;
import com.example.spotifyplaylistapp.model.entity.entity.Song;
import com.example.spotifyplaylistapp.model.entity.entity.Style;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SongService {

    private final SongRepository songRepository;

    private final StyleRepository styleRepository;

    private final UserRepository userRepository;

    private final StyleService styleService;


    public SongService(SongRepository songRepository, StyleRepository styleRepository, UserRepository userRepository, StyleService styleService) {
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
        this.styleService = styleService;
    }

    public void addSong(AddSongDTO addSongDTO) {
        Song song = new Song();
        song.setPerformer(addSongDTO.getPerformer());
        song.setTitle(addSongDTO.getTitle());
        song.setReleaseDate(addSongDTO.getReleaseDate());
        song.setDuration(Math.toIntExact(addSongDTO.getDuration()));
        Style style = this.styleService.findStyle(addSongDTO.getStyle());
        song.setStyle(style);
        this.songRepository.save(song);
    }
}
