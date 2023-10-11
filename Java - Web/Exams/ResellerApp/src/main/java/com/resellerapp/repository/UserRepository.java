package com.resellerapp.repository;

import com.resellerapp.model.dto.CurrentUserDTO;
import com.resellerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String value);
    @Query("select u from User as u" +
            " where u.email = :email")
    Optional<User> findByEmail(String email);
    @Query("select new com.resellerapp.model.dto.CurrentUserDTO(" +
            "u.username as username)" +
            " from User as u" +
            " where u.id = :id")
    Optional<CurrentUserDTO> findByUserId(Long id);

    User findByOffersId(Long id);
}
