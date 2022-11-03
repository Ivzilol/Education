package com.example.exercises_07_json_procesing.productshop.services;

import com.example.exercises_07_json_procesing.productshop.entities.categories.CategoryStatsDTO;
import com.example.exercises_07_json_procesing.productshop.entities.products.ProductWithoutBayerDTO;

import java.util.List;

public interface ProductService {
    List<ProductWithoutBayerDTO> getProductsInPriceRangeForSell(
            float from, float to);

    List<CategoryStatsDTO> getCategoryStatistics();
}
