package com.example.exercises_08_xml.productshop.services;

import com.example.exercises_08_xml.productshop.entities.users.ExportSellersDTO;
import com.example.exercises_08_xml.productshop.entities.users.ExportSellersWithCountsDTO;

public interface UserService {


    ExportSellersDTO findAllWithSoldProducts();

    ExportSellersWithCountsDTO findAllWithSoldProductsAndCounts();
}
