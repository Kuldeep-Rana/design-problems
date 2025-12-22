package com.reactivespring.service;

import com.reactivespring.client.MovieInfoClient;
import com.reactivespring.client.MovieReviewClient;
import com.reactivespring.dto.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MovieService {
    @Autowired
    private MovieInfoClient infoClient;

    @Autowired
    private MovieReviewClient reviewClient;

    public Mono<Movie> getMovieDetails(String movieId){

        return infoClient.getMovieInfo(movieId)
                .flatMap(movieInfo -> {
                    var movieReviews = reviewClient.getMovieInfo(movieId).collectList();
                    return  movieReviews.map(reviews -> new Movie(movieInfo, reviews));
                });

    }
}
