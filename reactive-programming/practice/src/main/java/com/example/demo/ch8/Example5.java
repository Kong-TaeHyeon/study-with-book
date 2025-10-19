package com.example.demo.ch8;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class Example5 {
    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofMillis(1L))
                .doOnNext(data -> log.info("# emitted by original Flux : {}", data))
                .onBackpressureBuffer(2,
                        dropped -> log.info(" ** Overflow & Dropped : {} **", dropped),
                        BufferOverflowStrategy.DROP_LATEST
                )
                .doOnNext(data -> log.info("[ # emitted by Buffer : {} ]",data))
                .publishOn(Schedulers.parallel(), false, 2)
                .subscribe(data -> {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {}
                    log.info("# onNext : {}", data);
                }, error -> log.info("# onError", error));


        Thread.sleep(3000L);
    }
}
