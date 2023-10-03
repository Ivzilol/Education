package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.entity.Song;
import com.example.spotifyplaylistapp.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    private final SongRepository songRepository;

    public HomeService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAllPop() {
        return this.songRepository.findPopSong();
    }

    public List<Song> findAllRock() {
        return this.songRepository.findRockSongs();
    }

    public List<Song> findAllJazz() {
        return this.songRepository.findJazzSongs();
    }
}
