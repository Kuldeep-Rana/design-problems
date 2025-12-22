package com.reactivespring.client;

import com.reactivespring.entity.MovieInfo;
import com.reactivespring.entity.MovieReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MovieReviewClient {

    @Autowired
    private WebClient webClient;

    @Value("${movie.review.baseurl}")
    private String movieReviewBaseUrl;


    public Flux<MovieReview> getMovieInfo(String movieId){
        String url = movieReviewBaseUrl+movieId;
        return  webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(MovieReview.class);
    }
}
