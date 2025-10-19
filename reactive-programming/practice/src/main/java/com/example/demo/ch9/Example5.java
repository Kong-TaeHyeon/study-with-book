package com.example.demo.ch9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class Example5 {
    public static void main(String[] args) {
        Sinks.Many<Integer> multicastSink =
                Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> fluxView = multicastSink.asFlux();

        multicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        multicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1 : {}", data));
        fluxView.subscribe(data -> log.info("# Subscriber2 : {}", data));

        multicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

    }
}
