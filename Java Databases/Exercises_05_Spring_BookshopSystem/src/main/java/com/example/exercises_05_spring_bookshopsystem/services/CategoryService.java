package com.example.exercises_05_spring_bookshopsystem.services;

import com.example.exercises_05_spring_bookshopsystem.entities.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
