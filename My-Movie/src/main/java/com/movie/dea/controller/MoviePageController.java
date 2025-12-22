package com.movie.dea.controller;


import com.movie.dea.entity.Movie;
import com.movie.dea.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MoviePageController {
    private final MovieService movieService;


    public MoviePageController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public String list(Model model) {
        model.addAttribute("movies", movieService.getAllMovie());
        return "movies/list";
    }

    //form of adding
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("movie", new Movie());
        return "movies/new";
    }


    @PostMapping
    public String save(@ModelAttribute Movie movie) {
        movieService.createMovie(movie);
        return "redirect:/movies";
    }


    // form of update
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("movie", movieService.getMovie(id));
        return "movies/edit";
    }

}
