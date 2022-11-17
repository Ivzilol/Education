package com.example.exercises_07_json_procesing.productshop.services;

import com.example.exercises_07_json_procesing.productshop.entities.categories.CategoryStatsDTO;
import com.example.exercises_07_json_procesing.productshop.entities.products.ProductWithoutBayerDTO;
import com.example.exercises_07_json_procesing.productshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

   private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductWithoutBayerDTO> getProductsInPriceRangeForSell(float from, float to) {
        BigDecimal rangeStart = BigDecimal.valueOf(from);
        BigDecimal rangeEnd = BigDecimal.valueOf(to);
        return this.productRepository.findAllByPriceBetweenAndBayerIsNullOrderByPriceAsc(
                rangeStart, rangeEnd);
    }

    @Override
    public List<CategoryStatsDTO> getCategoryStatistics() {
        return this.productRepository.getCategoryStats();
    }
}
