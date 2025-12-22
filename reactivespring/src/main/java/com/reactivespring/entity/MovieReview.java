package com.reactivespring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movie_review")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieReview {

    @Id
    private String reviewId;
    private String movieInfoId;
    private String comment;
    private int rating;
}
