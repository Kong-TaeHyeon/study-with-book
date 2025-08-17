package com.example.demo.ch6;

import reactor.core.publisher.Flux;

public class Example7 {
    public static void main(String[] args) {
        Flux.concat(
                Flux.just("Mercury", "Venus", "Earth"),
                Flux.just("Mars", "Jupiter", "Saturn"),
                Flux.just("Uranus", "Neptune", "Pluto")
        ).collectList()
                .subscribe(data -> System.out.println(data));
    }
}
