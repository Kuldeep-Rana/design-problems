package com.reactivespring.client;

import com.reactivespring.entity.MovieInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MovieInfoClient {

    @Autowired
    private WebClient webClient;



    public Mono<MovieInfo> getMovieInfo(String movieId){
        String url = "http://localhost:8080/api/v1/movieinfos/"+movieId;
        return  webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(MovieInfo.class);
    }
}
