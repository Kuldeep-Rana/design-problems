package com.reactivespring.controller;

import com.reactivespring.client.MovieInfoClient;
import com.reactivespring.client.MovieReviewClient;
import com.reactivespring.dto.Movie;
import com.reactivespring.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movie")
public class MovieServiceController {

    @Autowired
    MovieService service;

    @GetMapping("/{movieInfoId}")
    public Mono<Movie> getMovieDetails(@PathVariable String movieInfoId){
        return service.getMovieDetails(movieInfoId);
    }
}
