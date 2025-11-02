package com.example.demo.ch10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example10 {
    public static void main(String[] args) throws InterruptedException {
        doTask("Task 1")
                .subscribe(data -> log.info("# onNext : {}", data));

        doTask("Task 2")
                .subscribe(data -> log.info("# onNext : {}", data));

        Thread.sleep(200L);
    }

    private static Flux<Integer> doTask(String taskName) {
        return Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .publishOn(Schedulers.single())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# {} doOnNext filter : {}", taskName, data))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# {} doOnNext map : {}", taskName, data));
    }
}
