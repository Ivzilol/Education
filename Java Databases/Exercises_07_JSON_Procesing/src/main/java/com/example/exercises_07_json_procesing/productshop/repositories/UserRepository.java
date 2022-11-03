package com.example.exercises_07_json_procesing.productshop.repositories;

import com.example.exercises_07_json_procesing.productshop.entities.users.User;
import com.example.exercises_07_json_procesing.productshop.entities.users.UserWithSoldProductsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u" +
            " JOIN u.sellingItems p" +
            " WHERE p.bayer IS NOT NULL" +
            " ORDER BY size(u.sellingItems) DESC, u.lastName ASC")
    List<User> findAllWithSoldProducts();
}
