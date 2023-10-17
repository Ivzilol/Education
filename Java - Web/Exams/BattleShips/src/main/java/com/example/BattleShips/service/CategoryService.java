package com.example.BattleShips.service;

import com.example.BattleShips.model.entity.Category;
import com.example.BattleShips.model.enums.Name;
import com.example.BattleShips.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void initCategories() {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(Name.values())
                .forEach(c -> {
                    Category category = new Category();
                    category.setName(c);
                    switch (c.getValue()) {
                        case "Battle", "Cargo", "Patrol" -> category.setDescription("...");
                    }
                    this.categoryRepository.save(category);
                });
    }
}
