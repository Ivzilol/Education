package com.likebookapp.repository;

import com.likebookapp.model.entity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String value);

    Optional<User> findByUsername(String value);
}
