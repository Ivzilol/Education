
package com.likebookapp.init;

import com.likebookapp.service.MoodServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitMood implements CommandLineRunner {

    private final MoodServiceImpl moodService;

    public InitMood(MoodServiceImpl moodService) {
        this.moodService = moodService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.moodService.initMoods();
    }
}
