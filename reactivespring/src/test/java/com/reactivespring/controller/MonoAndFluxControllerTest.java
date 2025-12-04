package com.reactivespring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;


@WebFluxTest(MonoAndFluxController.class)
@AutoConfigureWebTestClient
class MonoAndFluxControllerTest {

    @Autowired
    WebTestClient client;

    @Test
    void test_mono() {
       Flux<String> a = client.get().uri("/mono").exchange().returnResult(String.class).getResponseBody();
        StepVerifier.create(a).expectNext("hello world").verifyComplete();
    }

    @Test
    void test_mono_1() {
        client.get().uri("/mono").exchange().expectBody(String.class).consumeWith( a ->{
           assertEquals(a.getResponseBody(),"hello world");
        });

    }

    @Test
    void test_flux_int() {
        Flux<Integer> b = client.get().uri("/flux-int").exchange().returnResult(Integer.class).getResponseBody();
        StepVerifier.create(b).expectNext(1,2,3).verifyComplete();
    }

    @Test
    void test_flux() {
        Flux<String> b = client.get().uri("/flux").exchange().returnResult(String.class).getResponseBody();
        StepVerifier.create(b).expectNext("SundayMondayTuesday").verifyComplete();
    }


    @Test
    void test_stream() {
        Flux<Long> st = client.get().uri("/stream").exchange().returnResult(Long.class).getResponseBody();
        StepVerifier.create(st).expectNext(0L,1L,2L,3L).thenCancel().verify();
    }
}