package com.example.exercises_07_json_procesing.productshop.services;

import com.example.exercises_07_json_procesing.productshop.entities.users.UserWithSoldProductsDTO;

import java.util.List;

public interface UserService {

    List<UserWithSoldProductsDTO> getUsersWithSoldProducts();
}
