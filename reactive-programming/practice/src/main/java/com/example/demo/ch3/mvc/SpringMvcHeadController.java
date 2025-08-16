package com.example.demo.ch3.mvc;

import com.example.demo.ch3.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/v1/books")
public class SpringMvcHeadController {
    private final RestTemplate restTemplate;

    URI baseUri = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port(8080)
            .build()
            .encode()
            .toUri();

    public SpringMvcHeadController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<Book> getBook(@PathVariable("book-id") long bookId) {
        log.info("Thread Name : {}", Thread.currentThread().getName());
        URI getBookUri = UriComponentsBuilder.fromUri(baseUri)
                .path("/branch/{book-id}")
                .build()
                .expand(bookId)
                .encode()
                .toUri();

        ResponseEntity<Book> response = restTemplate.getForEntity(getBookUri, Book.class);
        Book book = response.getBody();

        return ResponseEntity.ok(book);
    }
}
