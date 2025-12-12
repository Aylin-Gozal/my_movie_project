package com.movie.dea.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_details")
public class MovieDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Integer duration;  // minutes

    public MovieDetails(){

    }

    public MovieDetails(Integer id, String description, Integer duration) {
        this.id = id;
        this.description = description;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Movie movie;
}

