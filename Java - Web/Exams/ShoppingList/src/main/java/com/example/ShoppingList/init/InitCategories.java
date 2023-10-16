package com.example.ShoppingList.init;


import com.example.ShoppingList.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitCategories implements CommandLineRunner {

    private final CategoryService categoryService;

    public InitCategories(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initCategory();
    }
}
