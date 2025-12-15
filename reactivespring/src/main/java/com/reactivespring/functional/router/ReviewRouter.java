package com.reactivespring.functional.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ReviewRouter {

    @Bean
    public RouterFunction<ServerResponse> getReviews(){
        return route().GET("/api/view/reviews", (request ->
            ServerResponse.ok().bodyValue("List all the reviews")
        )).build();

    }
}
