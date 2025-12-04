package com.reactivespring.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class MonoAndFluxController {

    @GetMapping("/mono")
    public Mono<String> mono(){
        return Mono.just("hello world").log();
    }

    @GetMapping("/flux")
    public Flux<String> flux(){
        return Flux.just("Sunday", "Monday","Tuesday").log();
    }

    @GetMapping("/flux-int")
    public Flux<Integer> flux_int(){
        return Flux.just(1,2,3).log();
    }


    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> stream(){
        return Flux.interval(Duration.ofSeconds(1l)).log();
    }


}
