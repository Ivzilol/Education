package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("select s from Song as s" +
            " where s.style.styleName = 'POP'")
    List<Song> findPopSong();
    @Query("select s from Song as s" +
            " where s.style.styleName = 'ROCK'")
    List<Song> findRockSongs();

    @Query("select s from Song as s" +
            " where s.style.styleName = 'JAZZ'")
    List<Song> findJazzSongs();
}
