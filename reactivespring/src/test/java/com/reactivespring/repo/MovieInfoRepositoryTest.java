package com.reactivespring.repo;

import com.reactivespring.entity.MovieInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

@DataMongoTest
@ActiveProfiles("test")
class MovieInfoRepositoryTest {

    @Autowired
    MovieInfoRepository movieInfoRepository;

    @BeforeEach
    void setUp() {

        List<MovieInfo> movies = List.of(
                new MovieInfo("M001", "The Silent Horizon", 2021,
                        List.of("Arjun Mehta", "Sara Khan", "Rohit Kapoor"),
                        LocalDate.parse("2021-07-16")),

                new MovieInfo("M002", "Echoes of Tomorrow", 2022,
                        List.of("Neha Sharma", "Vikram Rao"),
                        LocalDate.parse("2022-03-11")),

                new MovieInfo("M003", "Midnight Pulse", 2020,
                        List.of("Karan Patel", "Meera Joshi", "Siddharth Bansal"),
                        LocalDate.parse("2020-11-05")),

                new MovieInfo("M004", "Shadows Unbound", 2019,
                        List.of("Aisha Verma", "Kabir Singh"),
                        LocalDate.parse("2019-08-23")),

                new MovieInfo("M005", "Neon Dreams", 2023,
                        List.of("Rhea Nair", "Aditya Roy", "Lakshya Kapoor"),
                        LocalDate.parse("2023-01-14"))
        );

        movieInfoRepository.saveAll(movies).blockLast();
    }


    @AfterEach
    void tearDOwn(){
        movieInfoRepository.deleteAll().block();
    }


    @Test
    void test_findAll(){
        var movies  = movieInfoRepository.findAll().log();
        StepVerifier.create(movies).expectNextCount(5).verifyComplete();
    }

    @Test
    void tet_findById(){
        Mono<MovieInfo> m005 = movieInfoRepository.findById("M005");
        StepVerifier.create(m005)
                .assertNext(a -> a.getName().equals("Neon Dreams"))
                .verifyComplete();
    }

    @Test
    void test_save(){
        var movie = new MovieInfo(null, "Neon Dreams 1", 2023,
                List.of("Rhea Nair", "Aditya Roy", "Lakshya Kapoor"),
                LocalDate.parse("2024-01-14"));

        var savedRecord = movieInfoRepository.save(movie);
        StepVerifier.create(savedRecord)
                .assertNext(m -> Assertions.assertNotNull(m.getMovieInfoId()))
                .verifyComplete();

    }


}