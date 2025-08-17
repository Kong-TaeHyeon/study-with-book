package com.example.demo.ch6;

import reactor.core.publisher.Mono;

public class Example2 {
    public static void main(String[] args) {
        Mono
                .empty()
                .subscribe(
                        none -> System.out.println("# emmitted onNext signal"),
                        error -> {},
                        () -> System.out.println("# emitted onComplete Signal")
                );
    }
}
