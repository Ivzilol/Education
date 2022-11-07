package com.example.books.service;

import com.example.books.model.dto.AuthorDTO;
import com.example.books.model.dto.BookDTO;
import com.example.books.model.entity.BookEntity;
import com.example.books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {


    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }
    public Optional<BookDTO> getBookById(Long bookId) {
        return bookRepository
                .findById(bookId)
                .map(this::map);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public Long createBook(BookDTO bookDTO) {

        return 50L;
    }

    private BookDTO map(BookEntity bookEntity) {
        return new
                BookDTO()
                .setId(bookEntity.getId())
                .setTitle(bookEntity.getTitle())
                .setIsbn(bookEntity.getIsbn())
                .setAuthor(new AuthorDTO().setName(bookEntity.getAuthor().getName()));
    }
}
