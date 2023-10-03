package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
    @Query("select s from Style as s" +
            " where s.styleName = :style")
    Style findStyle(StyleName style);

    @Query("select s from Style as s" +
            " where s.styleName = :style")
    Optional<Style> findByStyleName(StyleName style);
}
