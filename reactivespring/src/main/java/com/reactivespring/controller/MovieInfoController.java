package com.reactivespring.controller;

import com.reactivespring.entity.MovieInfo;
import com.reactivespring.service.MovieInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class MovieInfoController {

    @Autowired
    private  MovieInfoService movieInfoService;
    @PostMapping("/movieinfos")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovieInfo> addMovieInfo(@RequestBody MovieInfo movieInfo){
            return movieInfoService.saveMovieInfo(movieInfo);
    }


    @DeleteMapping("/movieinfos/{movieInfoId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteMovieInfo(@PathVariable String movieInfoId){
        return movieInfoService.deleteMovie(movieInfoId);
    }




}
