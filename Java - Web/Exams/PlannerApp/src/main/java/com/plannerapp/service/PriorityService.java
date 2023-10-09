package com.plannerapp.service;

import com.plannerapp.model.entity.entity.Priority;
import com.plannerapp.model.entity.enums.PriorityName;
import com.plannerapp.repo.PriorityRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public void initPriority() {
        if (priorityRepository.count() != 0) {
            return;
        }

        Arrays.stream(PriorityName.values())
                .forEach(p -> {
                    Priority priority = new Priority();
                    priority.setPriorityName(p);
                    switch (p.getValue()) {
                        case "Urgent":
                            priority.setDescription("An urgent problem that blocks the system use until the issue is resolved.");
                            break;
                        case "Important":
                            priority.setDescription("A core functionality that your product is explicitly supposed to perform is compromised.");
                            break;
                        case "Low":
                            priority.setDescription("Should be fixed if time permits but can be postponed.");
                            break;
                    }
                    this.priorityRepository.save(priority);
                });
    }
}
