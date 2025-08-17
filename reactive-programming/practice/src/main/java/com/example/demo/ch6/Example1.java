package com.example.demo.ch6;

import reactor.core.publisher.Mono;

public class Example1 {
    public static void main(String[] args) {
        Mono.just("Hello Reactor")
                .subscribe(data -> System.out.println(data));
    }
}
