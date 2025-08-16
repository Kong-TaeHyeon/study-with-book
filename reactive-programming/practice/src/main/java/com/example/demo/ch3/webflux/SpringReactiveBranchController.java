package com.example.demo.ch3.webflux;

import com.example.demo.ch3.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/branch/v2")
@Slf4j
public class SpringReactiveBranchController {
    private Map<Long, Book> bookMap;

    public SpringReactiveBranchController() {
        bookMap = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            Long id = Long.parseLong(i + "");
            bookMap.put(id, new Book(id, "title : " + i));
        }
    }

    @GetMapping("/{book-id}")
    public Mono<Book> getBook(@PathVariable("book-id") long bookId) throws InterruptedException {
        log.info("Thread Name : {}" , Thread.currentThread().getName());
        Thread.sleep(5000);

        Book book = bookMap.get(bookId);

        return Mono.just(book);
    }
}
