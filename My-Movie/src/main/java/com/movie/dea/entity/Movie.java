package com.movie.dea.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title is required!")
    private String title;

    @NotBlank(message = "Genre is required!")
    private String genre;

    @NotNull(message = "Release date is required!")
    private LocalDate releaseDate;

    @NotNull(message = "Rating date is required!")
    private Double rating;

    @NotNull(message = "Duration is required!")
     private Integer duration;

    public Movie() {

    }


    public Movie(Integer id, String title, String genre, LocalDate releaseDate, Double rating, Integer duration) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getDuration() {
        return duration;
    }

     public void setDuration(Integer duration) {
         this.duration = duration;
    }

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
    private MovieDetails movieDetails;
}
