package com.movie.dea.service;


import com.movie.dea.entity.MovieDetails;
import com.movie.dea.repository.MovieDetailsRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MovieDetailsService {
    private final MovieDetailsRepository movieDetailsRepository;


    public MovieDetailsService(MovieDetailsRepository movieDetailsRepository) {
        this.movieDetailsRepository = movieDetailsRepository;
    }

    public List<MovieDetails> getAllDetails(){
        return movieDetailsRepository.findAll();
    }

    public MovieDetails getDetailsById(@PathVariable Integer id) {
        return movieDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("..." + id));
    }

    public MovieDetails createDetails(@RequestBody MovieDetails newMovieDetails) {
        return movieDetailsRepository.save(newMovieDetails);
    }
}
