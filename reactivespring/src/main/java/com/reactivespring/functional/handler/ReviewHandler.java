package com.reactivespring.functional.handler;

import com.reactivespring.entity.MovieReview;
import com.reactivespring.repo.MovieReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReviewHandler {

    @Autowired
    private MovieReviewRepository movieReviewRepository;

    public Mono<ServerResponse> addReview(ServerRequest request) {
            return  request.bodyToMono(MovieReview.class)
                .flatMap(review -> {
                    System.out.println("reviews "+review);
                    return movieReviewRepository.save(review); // call the save
                })
                 .flatMap(review -> ServerResponse.status(HttpStatus.CREATED).bodyValue(review)); // transform into ServerResponse from MovieReview
    }

    public Mono<ServerResponse> allReviews(ServerRequest request){
        Flux<MovieReview> reviews = movieReviewRepository.findAll();
        return ServerResponse.ok().body(reviews, MovieReview.class);
    }

}
