package com.example.exercises_05_spring_bookshopsystem.repositories;

import com.example.exercises_05_spring_bookshopsystem.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
