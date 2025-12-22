package com.reactivespring.dto;

import com.reactivespring.entity.MovieInfo;
import com.reactivespring.entity.MovieReview;

import java.util.List;

public class Movie {

    private MovieInfo movieInfo;
    private List<MovieReview> reviews;

    public Movie(MovieInfo movieInfo, List<MovieReview> reviews) {
        this.movieInfo = movieInfo;
        this.reviews = reviews;
    }

    public MovieInfo getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(MovieInfo movieInfo) {
        this.movieInfo = movieInfo;
    }

    public List<MovieReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<MovieReview> reviews) {
        this.reviews = reviews;
    }
}
