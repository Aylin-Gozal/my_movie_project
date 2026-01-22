package com.movie.dea.controller;


import com.movie.dea.dto.MovieForm;
import com.movie.dea.entity.Movie;
import com.movie.dea.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MoviePageController {
    private final MovieService movieService;


    public MoviePageController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public String list(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(defaultValue = "title") String sortby,
            @RequestParam(defaultValue = "asc") String direction,
            Model model
    ) {
        Sort sort = direction.equals("asc")
                ? Sort.by(sortby).ascending()
                : Sort.by(sortby).descending();
        List<Movie> movies = movieService.getAllMovie();

        model.addAttribute("movies", movieService.search(title, genre, sort));
        model.addAttribute("sortBy",sortby);
        model.addAttribute("direction",direction);
        model.addAttribute("title", title);
        model.addAttribute("genre", genre);
        return "movies/list";
    }

    //form of adding
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("movieForm", new MovieForm());
        return "movies/new";
    }


    @PostMapping
    public String save(@Valid @ModelAttribute("movieForm") MovieForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return form.getId() == null ?  "movies/new" : "movies/edit";
        }


        Movie movie;

        if (form.getId() == null) {
            movie = new Movie();
        } else {
            movie = movieService.getMovie(form.getId());
        }

        movie.setId(form.getId());
        movie.setTitle(form.getTitle());
        movie.setGenre(form.getGenre());
        movie.setReleaseDate(form.getReleaseDate());
        movie.setRating(form.getRating());
        movie.setDuration(form.getDuration());

        movieService.createMovie(movie);
        return "redirect:/movies";
    }


    // form of update
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {


        Movie movie = movieService.getMovie(id);
        MovieForm form = new MovieForm();
        form.setId(movie.getId());
        form.setTitle(movie.getTitle());
        form.setGenre(movie.getGenre());
        form.setReleaseDate(movie.getReleaseDate());
        form.setRating(movie.getRating());
        form.setDuration(movie.getDuration());

        model.addAttribute("movieForm", form);
        return "movies/edit";
    }
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Integer id,
                         @Valid @ModelAttribute Movie movie,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("movie", movie); // keep the form values
            return "movies/edit";
        }

        // call your existing service
        movieService.updateMovie(id, movie);

        return "redirect:/movies"; // redirect to the list after saving
    }




    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id){
        movieService.deleteById(id);
        return "redirect:/movies";
    }


}
