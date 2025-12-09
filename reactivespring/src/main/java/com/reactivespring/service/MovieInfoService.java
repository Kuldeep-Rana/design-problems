package com.reactivespring.service;

import com.reactivespring.entity.MovieInfo;
import com.reactivespring.repo.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
