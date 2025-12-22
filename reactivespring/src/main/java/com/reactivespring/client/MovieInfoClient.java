package com.reactivespring.client;

import com.reactivespring.entity.MovieInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MovieInfoClient {

    @Autowired
    private WebClient webClient;

    @Value("${movie.info.baseurl}")
    private String movieInfoBaseUrl;

    public Mono<MovieInfo> getMovieInfo(String movieId){
        String url = movieInfoBaseUrl+movieId;
        return  webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(MovieInfo.class);
    }
}
