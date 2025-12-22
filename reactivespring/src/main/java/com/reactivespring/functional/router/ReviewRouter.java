package com.reactivespring.functional.router;

import com.reactivespring.functional.handler.ReviewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ReviewRouter {

    @Bean
    public RouterFunction<ServerResponse> getReviews(ReviewHandler reviewHandler){
        return route()
                .GET("/api/reviews", (reviewHandler::allReviews))
                .GET("{/api/reviews", (reviewHandler::allReviews))
                .POST("/api/reviews", reviewHandler::addReview)
                .build();

    }
}
