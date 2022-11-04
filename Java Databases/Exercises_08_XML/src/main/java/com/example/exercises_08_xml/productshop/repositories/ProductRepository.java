package com.example.exercises_08_xml.productshop.repositories;

import com.example.exercises_08_xml.productshop.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByPriceBetweenAndBayerIsNullOrderByPriceDesc
            (BigDecimal rangeFrom, BigDecimal rangeTo);
}
