package com.example.demo.ch10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example6 {
    public static void main(String[] args) throws InterruptedException {

        Flux
                .fromArray(new Integer[] {1, 3, 5, 7, 9, 13, 15, 17})
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(data -> log.info("# doOnNext fromArray : {}", data))
                .parallel()
                .runOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# doOnNext filter : {}", data))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# doOnNext map : {}", data))
                .subscribe(data -> log.info("# onNext : {}", data));

        Thread.sleep(500L);
    }
}
