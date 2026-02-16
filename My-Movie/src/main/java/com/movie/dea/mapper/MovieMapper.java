package com.movie.dea.mapper;

import com.movie.dea.dto.MovieDTO;
import com.movie.dea.dto.MovieForm;
import com.movie.dea.entity.Director;
import com.movie.dea.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    // Entity -> DTO -> http requests; list.html
    public MovieDTO toDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();


        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setGenre(movie.getGenre());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setRating(movie.getRating());
        dto.setDuration(movie.getDuration());

        if (movie.getDirector() != null) {
            dto.setDirectorId(movie.getDirector().getId());
            dto.setDirectorName(movie.getDirector().getName());
        }
        return dto;
    }

    // Entity to Form; new.html edit.html
    public MovieForm toForm(Movie movie) {
        MovieForm form = new MovieForm();

        form.setId(movie.getId());
        form.setTitle(movie.getTitle());
        form.setGenre(movie.getGenre());
        form.setReleaseDate(movie.getReleaseDate());
        form.setRating(movie.getRating());
        form.setDuration(movie.getDuration());

        if (movie.getDirector() != null) {
            form.setDirectorId(movie.getDirector().getId());
        }
        return form;

    }

    public void updatedEntityForm(MovieForm form, Movie movie, Director director){
        movie.setTitle(form.getTitle());
        movie.setGenre(form.getGenre());
        movie.setReleaseDate(form.getReleaseDate());
        movie.setRating(form.getRating());
        movie.setDuration(form.getDuration());
        movie.setDirector(director);
    }
}
