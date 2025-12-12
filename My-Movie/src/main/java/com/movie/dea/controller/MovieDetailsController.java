package com.movie.dea.controller;

import com.movie.dea.entity.MovieDetails;
import com.movie.dea.service.MovieDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/details")
public class MovieDetailsController {
    private final MovieDetailsService movieDetailsService;

    public MovieDetailsController(MovieDetailsService movieDetailsService) {
        this.movieDetailsService = movieDetailsService;
    }

    @GetMapping("/all")
    public List<MovieDetails> getAllDetails() {
        return movieDetailsService.getAllDetails();
    }

    @GetMapping("/{id}")
    public MovieDetails getDetailsById(@PathVariable Integer id) {
        return movieDetailsService.getDetailsById(id);
    }

    @PostMapping("/add")
    public MovieDetails createNewDetails(@RequestBody MovieDetails movieDetails) {
        return movieDetailsService.createDetails(movieDetails);
    }
}
