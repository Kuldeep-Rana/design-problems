package com.reactivespring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document("movie_info")
public class MovieInfo {

    @Id
    private String movieInfoId;
    private String name;

    private Integer year;

    private List<String> cast;

    private LocalDate releaseDate;
    public MovieInfo(){}
    public MovieInfo(String movieInfoId, String name, Integer year, List<String> cast, LocalDate releaseDate) {
        this.movieInfoId = movieInfoId;
        this.name = name;
        this.year = year;
        this.cast = cast;
        this.releaseDate = releaseDate;
    }

    public String getMovieInfoId() {
        return movieInfoId;
    }

    public void setMovieInfoId(String movieInfoId) {
        this.movieInfoId = movieInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "MovieInfo{" +
                "movieInfoId='" + movieInfoId + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", cast=" + cast +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
