package com.example.ms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/b")
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
                .uri("http://localhost:8083/api/v1/c/hello")
                .retrieve()
                .bodyToMono(String.class)
                .map(b -> "C -> " + b);
    }
}
