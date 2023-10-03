package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String value);

    Optional<User> findByEmail(String value);
}
