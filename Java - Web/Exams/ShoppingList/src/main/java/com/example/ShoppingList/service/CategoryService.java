package com.example.ShoppingList.service;

import com.example.ShoppingList.model.entity.Category;
import com.example.ShoppingList.model.enums.Name;
import com.example.ShoppingList.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void initCategory() {
        if (this.categoryRepository.count() != 0) {
            return;
        }
        Arrays.stream(Name.values())
                .forEach(c -> {
                    Category category = new Category();
                    category.setName(c);
                    switch (c.getValue()) {
                        case "Food", "Drink", "Household", "Other" -> category.setDescription("...");
                    }
                    this.categoryRepository.save(category);
                });
    }
}
