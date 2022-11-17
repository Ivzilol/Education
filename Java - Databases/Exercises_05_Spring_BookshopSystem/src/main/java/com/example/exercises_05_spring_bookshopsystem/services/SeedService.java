package com.example.exercises_05_spring_bookshopsystem.services;

import java.io.IOException;

public interface SeedService {



    void seedAuthors() throws IOException;

    void seedCategories() throws IOException;

    void seedBooks() throws IOException;

    default void seedAll() throws IOException {
        seedAuthors();
        seedCategories();
        seedBooks();
    }

}
