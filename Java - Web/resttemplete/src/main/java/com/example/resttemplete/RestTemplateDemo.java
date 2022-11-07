package com.example.resttemplete;

import com.example.resttemplete.model.BookDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class RestTemplateDemo implements CommandLineRunner {

    private final RestTemplate restTemplate;

    public RestTemplateDemo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) {
        ResponseEntity<BookDTO[]> allBooksResponse = restTemplate
                .getForEntity("http://localhost:8080/api/books", BookDTO[].class);
        if (allBooksResponse.hasBody()){
           for (BookDTO book : Objects.requireNonNull(allBooksResponse.getBody())){
               System.out.println("Book:" + book);
           }
        }

    }
}
