package com.example.exercises_05_spring_bookshopsystem.services;

import com.example.exercises_05_spring_bookshopsystem.entities.Author;
import com.example.exercises_05_spring_bookshopsystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = this.authorRepository.count();
        int  authorId = new Random().nextInt((int) size) + 1;
        return this.authorRepository.findById(authorId).get();
    }
}
