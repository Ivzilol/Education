package com.example.ShoppingList.repository;

import com.example.ShoppingList.model.entity.Category;
import com.example.ShoppingList.model.enums.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category as c" +
            " where c.name = :category")
    Category findCategory(Name category);
}
