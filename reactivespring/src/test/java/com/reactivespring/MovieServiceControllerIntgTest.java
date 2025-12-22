package com.reactivespring;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.reactivespring.dto.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.http.HttpHeaders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@AutoConfigureWireMock(port = 8084)
@TestPropertySource(
        properties = {
                "movie.info.baseurl=http://localhost:8084/api/v1/movieinfos/",
                "movie.review.baseurl=http://localhost:8084/api/reviews/"
        }
)
public class MovieServiceControllerIntgTest {

    @Autowired
    WebTestClient client;

    @Test
    void test_getMovieDetails(){

        var movieInfoId = "M2501";
        stubFor(get(urlEqualTo("/api/v1/movieinfos/"+movieInfoId))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("movieinfo.json")
                ));

        stubFor(get(urlEqualTo("/api/reviews/"+movieInfoId))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("moviereview.json")
                ));

        client.get().uri("/api/movie/{movieInfoId}", "M2501")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Movie.class)
                .consumeWith(m ->{
                    var movie = m.getResponseBody();
                    assertNotNull(movie);
                   assertEquals("Neon Dreams 0", movie.getMovieInfo().getName());
                   assertEquals(2, movie.getReviews().size());
                });

    }
}
