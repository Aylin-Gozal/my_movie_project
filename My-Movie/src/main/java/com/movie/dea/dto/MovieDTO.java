package com.movie.dea.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class MovieDTO {
    private Integer id;

//    @NotBlank(message = "Title is required!")
//    @Size(min=5, max=100, message = "Title must be 5-100 characters!")
    private String title;

//    @NotBlank(message = "Genre is required!")
//    @Size(min=5, max=100, message = "Genre must be 5-100 characters!")
    private String genre;

//    @NotNull(message = "Release date is required!")
    private LocalDate releaseDate;

//    @NotNull(message = "Rating date is required!")
//    @DecimalMin(value = "1.0", message = "Rating must be at least 0")
//    @DecimalMax(value = "10.0", message = "Rating must be maximum 10")
    private Double rating;

//    @NotNull(message = "Duration is required!")

//    @Min(value=20, message = "Duration must be 2-3 characters!")
//    @Max(value=1000, message = "Duration must be 2-3 characters!")
    private Integer duration;

//    @NotNull(message = "Shouldn't be null!")
    private Integer directorId;

    private String directorName;


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

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
}
