package com.example.books.repository;

import com.example.books.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

}
