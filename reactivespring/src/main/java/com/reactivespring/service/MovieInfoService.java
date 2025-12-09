package com.reactivespring.service;

import com.reactivespring.entity.MovieInfo;
import com.reactivespring.exception.RecordNotFoundException;
import com.reactivespring.repo.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieInfoService {
    @Autowired
    private MovieInfoRepository movieInfoRepository;

    public Mono<MovieInfo> saveMovieInfo(MovieInfo movieInfo){
        return movieInfoRepository.save(movieInfo);
    }

    public Mono<Void> deleteMovie(String movieInfoId) {
        return movieInfoRepository.deleteById(movieInfoId);
    }

    public Mono<MovieInfo> getMovieInfoById(String movieInfoId) {
        return movieInfoRepository.findById(movieInfoId).switchIfEmpty(Mono.error(new RecordNotFoundException("MovieInfo not found: " + movieInfoId)));
    }

    public Flux<MovieInfo> getMovieInfoByYear(Integer year) {
        return movieInfoRepository.findByYear(year);
    }

    public Flux<MovieInfo> getMovieInfoByName(String name) {
        return movieInfoRepository.findByNameLike(name);
    }
}
