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

    public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie updatedMovie){
        return movieRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedMovie.getTitle());
                    existing.setGenre(updatedMovie.getGenre());
                    existing.setReleaseDate(updatedMovie.getReleaseDate());
                    existing.setRating(updatedMovie.getRating());
                    existing.setDuration(updatedMovie.getDuration());
                    return movieRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("No such a movie with following ID: " + id));
    }

    public String deleteById(@PathVariable Integer id) {
        if(movieRepository.existsById(id)){
            movieRepository.deleteById(id);
            return "Movie deleted!";
        }
        return "Not found";
    }
}
