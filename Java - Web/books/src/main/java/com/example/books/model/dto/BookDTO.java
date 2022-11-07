package com.example.books.model.dto;

public class BookDTO {

    private long id;

    private String title;

    private String isbn;

    private AuthorDTO author;

    public long getId() {
        return id;
    }

    public BookDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public BookDTO setAuthor(AuthorDTO author) {
        this.author = author;
        return this;
    }
}
