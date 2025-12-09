package com.reactivespring.controller;

import com.reactivespring.entity.MovieInfo;
import com.reactivespring.service.MovieInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/movieinfos")
public class MovieInfoController {

    @Autowired
    private  MovieInfoService movieInfoService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovieInfo> addMovieInfo(@RequestBody MovieInfo movieInfo){
            return movieInfoService.saveMovieInfo(movieInfo);
    }


    @DeleteMapping("/{movieInfoId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteMovieInfo(@PathVariable String movieInfoId){
        return movieInfoService.deleteMovie(movieInfoId);
    }


    @GetMapping("/{movieInfoId}")
    public Mono<MovieInfo> getMovieInfoById(@PathVariable  String movieInfoId){
        return movieInfoService.getMovieInfoById(movieInfoId);
    }

    @GetMapping(params = "year")
    public Flux<MovieInfo> getMovieInfoByYear(@RequestParam Integer year){
        return movieInfoService.getMovieInfoByYear(year);
    }

    @GetMapping(params = "name")
    public Flux<MovieInfo> getMovieInfoByName(@RequestParam String name){
        return movieInfoService.getMovieInfoByName(name);
    }



}
