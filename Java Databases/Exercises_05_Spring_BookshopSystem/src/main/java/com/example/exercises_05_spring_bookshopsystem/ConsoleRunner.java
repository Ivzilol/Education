package com.example.exercises_05_spring_bookshopsystem;

import com.example.exercises_05_spring_bookshopsystem.entities.Author;
import com.example.exercises_05_spring_bookshopsystem.entities.Book;
import com.example.exercises_05_spring_bookshopsystem.repositories.AuthorRepository;
import com.example.exercises_05_spring_bookshopsystem.repositories.BookRepository;
import com.example.exercises_05_spring_bookshopsystem.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(
            SeedService seedService,
            BookRepository bookRepository,
            AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAuthors();
//        this.seedService.seedCategories();
//        this.seedService.seedAll();
//        this._01_booksAfter2000();
//        this._02_allAuthorsWithBooksBefore1990();
        this._03_allAuthorsOrderedByBookCount();
    }

    private void _03_allAuthorsOrderedByBookCount() {
        List<Author> authors = this.authorRepository.findAll();

        authors
                .stream()
                .sorted(Comparator.comparing(a -> a.getBooks().size()))
                .forEach(author ->
                        System.out.printf("%s %s -> %d%n",
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks().size()));

    }

    private void _02_allAuthorsWithBooksBefore1990() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);
        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);
        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void _01_booksAfter2000() {
        LocalDate year2000 = LocalDate.of(2000, 12, 31);
        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);

        books.forEach(b -> System.out.println(b.getTitle()));
        int count = this.bookRepository.countByReleaseDateAfter(year2000);
        System.out.printf("Total count: %d\n", count);
    }
}
