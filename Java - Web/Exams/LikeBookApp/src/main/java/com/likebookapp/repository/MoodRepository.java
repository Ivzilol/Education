package com.likebookapp.repository;

import com.likebookapp.model.entity.entity.Mood;
import com.likebookapp.model.entity.enums.MoodsEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MoodRepository extends JpaRepository<Mood, Long> {
    @Query("select m from Mood as m" +
            " where m.moodsName = :mood")
    Mood findMood(MoodsEnum mood);
}