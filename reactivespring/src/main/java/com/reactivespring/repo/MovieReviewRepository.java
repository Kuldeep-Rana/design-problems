package com.reactivespring.repo;

import com.reactivespring.entity.MovieReview;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovieReviewRepository extends ReactiveMongoRepository<MovieReview, String> {
    Flux<MovieReview> findByMovieInfoId(String movieInfoId);
}
