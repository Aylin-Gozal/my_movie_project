package com.movie.dea.service;

import com.movie.dea.entity.Movie;
import com.movie.dea.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    public Movie createMovie(@RequestBody Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    public Movie getMovie(@PathVariable Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No such a movie in db: " + id));
    }
}
