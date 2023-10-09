package com.plannerapp.repo;

import com.plannerapp.model.entity.dto.CurrentUserDTO;
import com.plannerapp.model.entity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User as u" +
            " where u.email = :email")
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String value);
    @Query("select new com.plannerapp.model.entity.dto.CurrentUserDTO(" +
            "u.username as username)" +
            " from User as u" +
            " where u.id = :id")
    Optional<CurrentUserDTO> findByUserId(Long id);
}
