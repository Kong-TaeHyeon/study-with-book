package com.example.demo.ch7;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class Example4 {
    public static void main(String[] args) {
        Flux<Integer> pub = Flux.range(1, 5)
                .doOnRequest(data -> log.info("# onRequest : {}", data));

        pub.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(1);
            }

            @SneakyThrows
            @Override
            protected void hookOnNext(Integer value) {
                Thread.sleep(2000L);
                log.info("# hookOnNext : {}", value);
                request(1);
            }
        });
    }
}
