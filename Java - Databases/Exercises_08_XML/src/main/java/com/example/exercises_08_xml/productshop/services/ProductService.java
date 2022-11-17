package com.example.exercises_08_xml.productshop.services;


import com.example.exercises_08_xml.productshop.entities.products.ExportProductsInRangeDTO;

public interface ProductService {

    ExportProductsInRangeDTO getInRange(float from, float to);
}
