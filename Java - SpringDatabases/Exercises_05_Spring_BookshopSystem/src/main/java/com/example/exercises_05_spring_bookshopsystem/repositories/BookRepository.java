package com.example.exercises_05_spring_bookshopsystem.repositories;

import com.example.exercises_05_spring_bookshopsystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByReleaseDateAfter(LocalDate releaseData);
    int countByReleaseDateAfter(LocalDate releaseData);
}
