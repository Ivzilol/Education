package com.resellerapp.service;

import com.resellerapp.model.entity.entity.Condition;
import com.resellerapp.model.entity.enums.ConditionName;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public void initCondition() {
        if (this.conditionRepository.count() != 0) {
            return;
        }
        Arrays.stream(ConditionName.values())
                .forEach(c -> {
                    Condition condition = new Condition();
                    condition.setConditionName(c);
                    switch (c.getValue()) {
                        case "Excellent":
                            condition.setDescription("In perfect condition");
                            break;
                        case "Good":
                            condition.setDescription("Some signs of wear and tear or minor defects");
                            break;
                        case "Acceptable":
                            condition.setDescription("The item is fairly worn but continues to function properly");
                            break;
                    }
                    this.conditionRepository.save(condition);
                });
    }
}
