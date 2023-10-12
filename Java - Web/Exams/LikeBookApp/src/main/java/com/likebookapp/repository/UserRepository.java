package com.likebookapp.repository;

import com.likebookapp.model.dto.PostsCurrentUserDTO;
import com.likebookapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String value);

    @Query("select u from User as u" +
            " where u.email = :email")
    Optional<User> findByEmail(String email);

    @Query("select u from User as u" +
            " where u.id = :loggedUserId")
    Optional<User> findUserById(Long loggedUserId);
}
