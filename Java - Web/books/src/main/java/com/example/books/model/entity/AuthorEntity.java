package com.example.books.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<BookEntity> books;

    public long getId() {
        return id;
    }

    public AuthorEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthorEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public AuthorEntity setBooks(List<BookEntity> books) {
        this.books = books;
        return this;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
