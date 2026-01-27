package com.movie.dea.controller;

import com.movie.dea.entity.Movie;
import com.movie.dea.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

//    @GetMapping("/all")
//    public List<Movie> getMovies() {
//        return movieService.getAllMovie(sort);
//    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Integer id) {
        return movieService.getMovie(id);
    }

    @PostMapping("/add")
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/update/{id}")
    public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteById(id);
    }

    @GetMapping("/title/{title}")
    public List<Movie> getAllMovieByTitle(@PathVariable String title) {
        return movieService.getAllMovieByTitle(title);
    }

    @GetMapping("/genre/{genre}")
    public List<Movie> getAllMovieByGenre(@PathVariable String genre) {
        return movieService.getAllMovieByGenre(genre);
    }

    @GetMapping("/rating/{rating}")
    public List<Movie> getAllMovieByRating(@PathVariable Double rating) {
        return movieService.getAllMovieByMinRating(rating);
    }

    @GetMapping("/date/{releaseDate}")
    public List<Movie> getMovieByDate(@PathVariable LocalDate releaseDate){
        return movieService.getAllMovieByReleaseDate(releaseDate);
    }


    @GetMapping("/pagination")
    public Page<Movie> getPagination(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "0") int size
        ) {
        return movieService.getMoviesByPage(page, size);
    }

}
