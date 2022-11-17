package com.example.exercises_07_json_procesing.productshop.repositories;

import com.example.exercises_07_json_procesing.productshop.entities.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
