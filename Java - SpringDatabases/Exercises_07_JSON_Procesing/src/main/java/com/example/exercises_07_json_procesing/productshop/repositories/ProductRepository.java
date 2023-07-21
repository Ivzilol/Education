package com.example.exercises_07_json_procesing.productshop.repositories;

import com.example.exercises_07_json_procesing.productshop.entities.categories.CategoryStatsDTO;
import com.example.exercises_07_json_procesing.productshop.entities.products.Product;
import com.example.exercises_07_json_procesing.productshop.entities.products.ProductWithoutBayerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT new " +
            "com.example.exercises_07_json_procesing.productshop.entities.products.ProductWithoutBayerDTO" +
            "(p.name, p.price, p.seller.firstName, p.seller.lastName) " +
            " FROM Product p " +
            " WHERE p.price > :rangeStart AND p.price < :rangeEnd AND p.bayer IS NULL" +
            " ORDER BY p.price ASC")
    List<ProductWithoutBayerDTO> findAllByPriceBetweenAndBayerIsNullOrderByPriceAsc(
            BigDecimal rangeStart, BigDecimal rangeEnd);

    @Query("SELECT new com.example.exercises_07_json_procesing.productshop.entities.categories.CategoryStatsDTO(" +
            "c.name, COUNT(p), AVG(p.price), SUM(p.price))" +
            " FROM Product p" +
            " JOIN p.categories c" +
            " GROUP BY c")
    List<CategoryStatsDTO> getCategoryStats();
}
