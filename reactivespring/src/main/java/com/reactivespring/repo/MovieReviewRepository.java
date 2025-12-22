package com.reactivespring.repo;

import com.reactivespring.entity.MovieReview;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieReviewRepository extends ReactiveMongoRepository<MovieReview, String> {
}
