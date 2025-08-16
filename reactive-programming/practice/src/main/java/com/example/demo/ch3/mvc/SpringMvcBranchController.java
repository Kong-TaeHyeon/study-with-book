package com.example.demo.ch3.mvc;

import com.example.demo.ch3.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/branch")
@Slf4j
public class SpringMvcBranchController {
    private Map<Long, Book> bookMap;

    public SpringMvcBranchController() {
        bookMap = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            Long id = Long.parseLong(i + "");
            bookMap.put(id, new Book(id, "title : " + i));
        }
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<Book> getBook(@PathVariable("book-id") long bookId) throws InterruptedException {
        log.info("Thread Name : {}", Thread.currentThread().getName());
        Thread.sleep(5000);

        Book book = bookMap.get(bookId);

        return ResponseEntity.ok(book);
    }
}
