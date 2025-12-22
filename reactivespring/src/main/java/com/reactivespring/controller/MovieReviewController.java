//package com.reactivespring.controller;
//
//import com.reactivespring.entity.MovieReview;
//import com.reactivespring.repo.MovieReviewRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
////@RestController
//@RequestMapping("/api/reviews")
//@RequiredArgsConstructor
//public class MovieReviewController {
//
//    private final MovieReviewRepository movieReviewRepository;
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Mono<MovieReview> createReview(@RequestBody MovieReview review) {
//        return movieReviewRepository.save(review);
//    }
//
//    @GetMapping
//    public Flux<MovieReview> getAllReviews() {
//        return movieReviewRepository.findAll();
//    }
//
//    @GetMapping("/movie/{movieInfoId}")
//    public Flux<MovieReview> getReviewsByMovie(@PathVariable String movieInfoId) {
//        return movieReviewRepository.findByMovieInfoId(movieInfoId);
//    }
//
//    // Get review by id
//    @GetMapping("/{reviewId}")
//    public Mono<MovieReview> getReviewById(@PathVariable String reviewId) {
//        return movieReviewRepository.findById(reviewId)
//                .switchIfEmpty(Mono.error(new RuntimeException("Review not found")));
//    }
//
//    @PutMapping("/{reviewId}")
//    public Mono<MovieReview> updateReview(
//            @PathVariable String reviewId,
//            @RequestBody MovieReview updatedReview) {
//
//        return movieReviewRepository.findById(reviewId)
//                .switchIfEmpty(Mono.error(new RuntimeException("Review not found")))
//                .flatMap(existing -> {
//                    existing.setComment(updatedReview.getComment());
//                    existing.setRating(updatedReview.getRating());
//                    existing.setMovieInfoId(updatedReview.getMovieInfoId());
//                    return movieReviewRepository.save(existing);
//                });
//    }
//
//    @DeleteMapping("/{reviewId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public Mono<Void> deleteReview(@PathVariable String reviewId) {
//        return movieReviewRepository.deleteById(reviewId);
//    }
//}
