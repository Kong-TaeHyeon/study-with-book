package com.example.demo.ch7;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Slf4j
public class Example1 {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> cold = Flux
                .fromIterable(Arrays.asList("KOREA", "JAPAN", "CHINESE"))
                .map(s -> {
                    log.info("Thread : {}, s : {}", Thread.currentThread().getName() , s);
                    return s.toLowerCase();
                });

        cold.subscribe(s -> log.info("Thread : {}, s : {}", Thread.currentThread().getName() , s));

        System.out.println("------------------");

        Thread.sleep(2000L);

        cold.subscribe(s -> log.info("Thread : {}, s : {}", Thread.currentThread().getName() , s));
    }
}
