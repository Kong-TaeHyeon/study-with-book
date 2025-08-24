package com.example.demo.ch7;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Example2 {

    public static void main(String[] args) throws InterruptedException {
        String[] singers = {"A", "B","C","D","E"};
        log.info("# Begin Concert");

        Flux<String> c1 = Flux
                .fromArray(singers)
                .delayElements(Duration.ofSeconds(1))
                .share();

        c1.subscribe(
                singer -> log.info("#1 : {}", singer)
        );

        Thread.sleep(2500);

        c1.subscribe(
                singer -> log.info("#2 : {}", singer)
        );

        Thread.sleep(3000);

    }
}
