package com.reactivespring.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactivespring.entity.MovieInfo;
import com.reactivespring.repo.MovieInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class MovieInfoControllerTest {

    public static final String URI = "/api/v1/movieinfos";
    @Autowired
    private WebTestClient client;

    @Autowired
    private MovieInfoRepository repository;

    @Autowired
    private ObjectMapper mapper;


    private List<MovieInfo> movies;

    @BeforeEach
    void setUp() throws IOException {
        movies = mapper.readValue(
                new ClassPathResource("movies.json").getInputStream(),
                new TypeReference<List<MovieInfo>>() {}
        );
        repository.saveAll(movies).blockLast();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll().block();
    }

    @Test
    void test_addMovieInfo() {
        var movie = new MovieInfo(null, "Neon Dreams 3", 2023,
                List.of("Rhea Nair", "Aditya Roy", "Lakshya Kapoor"),
                LocalDate.parse("2024-03-17"));

        client.post().uri(URI)
                .bodyValue(movie)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(MovieInfo.class)
                .consumeWith(m ->{
                   var info = m.getResponseBody();
                   assertNotNull(info.getMovieInfoId());
                   assertEquals("Neon Dreams 3",info.getName());
                });
    }

    @Test
    void test_deleteMovieInfo() {
        Flux<MovieInfo> movies = repository.findAll();
        StepVerifier.create(movies).expectNextCount(5L).verifyComplete();

        client.delete().uri(URI+"/M001")
                .exchange()
                .expectStatus().isOk();

        movies = repository.findAll();
        StepVerifier.create(movies).expectNextCount(4L).verifyComplete();
    }

    @Test
    void test_getMovieInfoById(){
        client.get().uri(URI+"/M001")
                .exchange()
                .expectStatus().isOk()
                .expectBody(MovieInfo.class)
                .consumeWith(m ->{
                  var info = m.getResponseBody();
                  assertEquals("M001",info.getMovieInfoId());
                  assertEquals("The Silent Horizon", info.getName());
                  assertEquals(2021, info.getYear());
                });

    }

    @Test
    void test_getMovieInfoById_noRecordFound(){
        client.get().uri(URI+"/M006")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .consumeWith(m ->{
                    String s = m.getResponseBody();
                    assertEquals("MovieInfo not found: M006", s);
                })
                ;
    }

    @Test
    void test_getMovieInfoByYear(){
        var url = UriComponentsBuilder.fromUriString(URI)
                .queryParam("year", 2021)
                .buildAndExpand().toUri();

        client.get().uri(url)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(MovieInfo.class)
                .consumeWith(m ->{
                    List<MovieInfo> movies = m.getResponseBody();
                    assertEquals(2, movies.size());
                })
        ;
    }

    @Test
    void test_getMovieInfoByName(){
        var url = UriComponentsBuilder.fromUriString(URI)
                .queryParam("name", "Midnight Pulse")
                .buildAndExpand().toUri();

        client.get().uri(url)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(MovieInfo.class)
                .consumeWith(m ->{
                    List<MovieInfo> movies = m.getResponseBody();
                    assertEquals(1, movies.size());
                    assertEquals("Midnight Pulse",movies.stream().findFirst().get().getName());
                })
        ;
    }



}