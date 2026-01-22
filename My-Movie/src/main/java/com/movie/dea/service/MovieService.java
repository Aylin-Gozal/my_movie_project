package com.movie.dea.service;

import com.movie.dea.entity.Movie;
import com.movie.dea.exception.MovieNotFoundException;
import com.movie.dea.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
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

    public List<Movie> getAllMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> getAllMovieByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public List<Movie> getAllMovieByMinRating(Double minRating) {
        return movieRepository.findByMinRating(minRating);
    }

    public List<Movie> getAllMovieByReleaseDate (LocalDate releaseDate){
        return movieRepository.findByReleaseDate(releaseDate);
    }

    public Movie createMovie(@RequestBody Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    public Movie getMovie(@PathVariable Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(()-> new MovieNotFoundException("No such a movie in db: " + id));
    }

    public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie updatedMovie){
        return movieRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedMovie.getTitle());
                    existing.setGenre(updatedMovie.getGenre());
                    existing.setReleaseDate(updatedMovie.getReleaseDate());
                    existing.setRating(updatedMovie.getRating());
//                    existing.setDuration(updatedMovie.getDuration());
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


    public Page<Movie> getMoviesByPage(@PathVariable int page, @PathVariable int size){
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll(pageable);
    }

    public List<Movie> search(@PathVariable String title, @PathVariable String genre, Sort sort){
        if (title != null && !title.isBlank()){
            return movieRepository.findByTitleContainingIgnoreCase(title);
        }

        if (genre != null && !genre.isBlank()){
            return movieRepository.findByGenre(genre);
        }

        return movieRepository.findAll(sort);
    }
}
