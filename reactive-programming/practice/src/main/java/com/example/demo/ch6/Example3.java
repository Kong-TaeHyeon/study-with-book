package com.example.demo.ch6;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;

public class Example3 {
    public static void main(String[] args) {
        URI worldTimeUri = UriComponentsBuilder.newInstance()
                .host("worldtimeapi.org")
                .scheme("https")
                .port(443)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Mono.just(
                restTemplate
                        .exchange(worldTimeUri, HttpMethod.GET, new HttpEntity<String>(headers), String.class)
        )
                .map(response ->
                    response.getBody()
                )
                .subscribe(
                        data -> System.out.println("# emitted data : " + data),
                        error -> System.out.println(error),
                        () -> System.out.println("# emiiteed onComplete signal")
                );
    }
}
