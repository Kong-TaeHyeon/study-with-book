package com.example.demo.ch5;

import reactor.core.publisher.Flux;

public class Example1 {
    public static void main(String[] args) {
        Flux<String> sequence = Flux.just("Hello", "World");
        sequence.map(s -> s.toUpperCase())
                .subscribe(System.out::println);
    }
}
