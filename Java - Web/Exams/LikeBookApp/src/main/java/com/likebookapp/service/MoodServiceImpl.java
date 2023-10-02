package com.likebookapp.service;

import com.likebookapp.model.entity.entity.Mood;
import com.likebookapp.model.entity.enums.MoodsEnum;
import com.likebookapp.repository.MoodRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class MoodServiceImpl implements MoodService {

    private final MoodRepository moodRepository;

    public MoodServiceImpl(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void initMoods() {
        if (this.moodRepository.count() != 0) {
            return;
        }
        Arrays.stream(MoodsEnum.values())
                .forEach(s -> {
                    Mood mood = new Mood();
                    mood.setMoodsName(s);
                    mood.setDescription("...");
                    moodRepository.save(mood);
                });
    }
}
