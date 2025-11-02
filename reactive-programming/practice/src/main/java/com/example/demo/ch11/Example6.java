package com.example.demo.ch11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example6 {
    public static void main(String[] args) throws InterruptedException {
        String key1 = "company";
        String key2 = "name";

        Mono
                .deferContextual(ctx ->
                        Mono.just(ctx.get(key1))
                )
                .publishOn(Schedulers.parallel())
                .transformDeferredContextual((mono, ctx) -> {
                    return mono.map(data -> data + ", " + ctx.getOrDefault(key2, "Steve"));
                })
                .contextWrite(context -> context.put(key2, "Bill"))
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext : {}", data));

        Thread.sleep(100L);
    }
}
