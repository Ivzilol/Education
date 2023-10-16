package com.example.ShoppingList.repository;

import com.example.ShoppingList.model.dto.*;
import com.example.ShoppingList.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String value);
    @Query("select new com.example.ShoppingList.model.dto.FoodDTO(" +
            " p.id, p.name as name, p.price) " +
            " from Product as p" +
            " join Category as c on p.category.id = c.id" +
            " where c.name = 'FOOD' and p.user.id = :userId")
    Set<FoodDTO> findProductFood(Long userId);
    @Query("select new com.example.ShoppingList.model.dto.DrinksDTO(" +
            " p.id, p.name as name, p.price) " +
            " from Product as p" +
            " join Category as c on p.category.id = c.id" +
            " where c.name = 'DRINK' and p.user.id = :id")
    Set<DrinksDTO> findProductDrinks(Long id);

    @Query("select new com.example.ShoppingList.model.dto.HouseholdsDTO(" +
            " p.id, p.name as name, p.price) " +
            " from Product as p" +
            " join Category as c on p.category.id = c.id" +
            " where c.name = 'HOUSEHOLD' and p.user.id = :id")
    Set<HouseholdsDTO> findHouseholdDTO(Long id);
    @Query("select new com.example.ShoppingList.model.dto.OthersDTO(" +
            " p.id, p.name as name, p.price) " +
            " from Product as p" +
            " join Category as c on p.category.id = c.id" +
            " where c.name = 'OTHER' and p.user.id = :id")
    Set<OthersDTO> findOthers(Long id);
    @Query("select new com.example.ShoppingList.model.dto.SumDTO(" +
            "sum(p.price) as sum)" +
            " from Product as p" +
            " where p.user.id = :id")
    SumDTO findSum(Long id);

    @Query("select p from Product as p" +
            " where p.user.id = :id")
    Set<Product> findAllProductCurrentUser(Long id);

}
