package com.example.demo.ch3.webflux;

import com.example.demo.ch3.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RequestMapping("/v2/books")
@RestController
public class SpringReactiveHeadController {
    URI baseUri = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("localhost")
            .port(8080)
            .build()
            .encode()
            .toUri();

    @GetMapping("/{book-id}")
    public Mono<Book> getBook(@PathVariable("book-id") long id) {
        log.info("Thread Name : {}" , Thread.currentThread().getName());
        URI getBookUri = UriComponentsBuilder.fromUri(baseUri)
                .path("/branch/v2/{book-id}")
                .build()
                .expand(id)
                .encode().toUri();

        return WebClient.create().get().uri(getBookUri).retrieve().bodyToMono(Book.class);
    }
}
