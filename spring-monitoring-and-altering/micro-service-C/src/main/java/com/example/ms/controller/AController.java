package com.example.ms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/c")
public class AController {
    private final WebClient webClient;

    public AController(WebClient.Builder wb) {
        this.webClient = wb.build();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello from A";
    }

    @GetMapping("/call-c")
    public Mono<String> callB() {
        return webClient.get()
                .uri("http://service-b:8083/api/v1/b/hello")
                .retrieve()
                .bodyToMono(String.class)
                .map(b -> "A -> " + b);
    }

    @GetMapping("/sleep")
    public String sleep(@RequestParam(defaultValue = "500") long ms) throws InterruptedException {
        Thread.sleep(ms);
        return "slept " + ms + "ms";
    }

    // Simulate occasional 5xx: ?rate=0.5  (50% chance)
    @GetMapping("/maybe-error")
    public String maybeError(@RequestParam(defaultValue = "0.2") double rate) {
        double r = Math.random();
        if (r < rate) {
            throw new RuntimeException("simulated server error");
        }
        return "ok";
    }
}
