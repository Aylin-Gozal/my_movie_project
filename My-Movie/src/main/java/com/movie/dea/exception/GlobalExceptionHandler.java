package com.movie.dea.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MovieNotFoundException.class)
    public String handleMovieNotFound(MovieNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "movies/error/not-found";
    }
}
