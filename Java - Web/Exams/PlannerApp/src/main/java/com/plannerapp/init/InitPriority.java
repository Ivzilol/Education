package com.plannerapp.init;


import com.plannerapp.service.PriorityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitPriority implements CommandLineRunner {

    private final PriorityService priorityService;

    public InitPriority(PriorityService priorityService) {
        this.priorityService = priorityService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.priorityService.initPriority();
    }
}
