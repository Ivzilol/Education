package com.example.exercises_08_xml.productshop.repositories;

import com.example.exercises_08_xml.productshop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u" +
            " WHERE " +
            " (SELECT COUNT(p) " +
            " FROM Product p " +
            " WHERE p.seller = u AND p.bayer IS  NOT NULL) > 0" +
            " order by u.lastName, u.firstName")
    List<User> findAllWithSoldProducts();

    @Query("select u from User u" +
            " WHERE " +
            " (SELECT COUNT(p) " +
            " FROM Product p " +
            " WHERE p.seller = u AND p.bayer IS  NOT NULL) > 0")
    List<User> findAllWithSoldProductsOrderByCount();
}
