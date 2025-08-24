package com.example.demo.ch7;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
public class Example3 {

    public static void main(String[] args) throws InterruptedException {
        URI wordTimeUri = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode().toUri();

        Mono<String> mono = getWorldTime(wordTimeUri);
        mono.subscribe(dataTime -> log.info("# 1 : {}", dataTime));
        Thread.sleep(2000);
        mono.subscribe(dataTime -> log.info("# 2 : {}", dataTime));
        Thread.sleep(2000);
    }

    private static Mono<String> getWorldTime(URI wordTimeUri) {
        return WebClient.create()
                .get()
                .uri(wordTimeUri)
                .retrieve()
                .bodyToMono(String.class);
    }
}
