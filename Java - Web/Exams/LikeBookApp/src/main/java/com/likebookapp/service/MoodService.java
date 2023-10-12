package com.likebookapp.service;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.enums.MoodName;
import com.likebookapp.repository.MoodRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MoodService {

    private final MoodRepository moodRepository;

    public MoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    public void initMood() {
        if (this.moodRepository.count() != 0) {
            return;
        }

        Arrays.stream(MoodName.values())
                .forEach(m -> {
                    Mood mood = new Mood();
                    mood.setMoodName(m);
                    switch (m.getValue()) {
                        case "Happy":
                            mood.setDescription("...");
                        case "Sad":
                            mood.setDescription("...");
                        case "Inspired":
                            mood.setDescription("...");
                            break;
                    }
                    this.moodRepository.save(mood);
                });
    }
}
