package com.movie.dea.service;

import com.movie.dea.dto.MovieForm;
import com.movie.dea.entity.Director;
import com.movie.dea.entity.Movie;
import com.movie.dea.exception.MovieNotFoundException;
import com.movie.dea.mapper.MovieMapper;
import com.movie.dea.repository.DirectorRepository;
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
    private final DirectorRepository directorRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, DirectorRepository directorRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.movieMapper = movieMapper;
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

    public Director getDirector(Integer id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Director with id: " + id));
    }




    public void saveForm(MovieForm movieForm) {
        Movie movie;

        if(movieForm.getId() == null) {
            movie = new Movie();
        } else{
            movie = getMovie(movieForm.getId());
        }

        Director director = getDirector(movieForm.getDirectorId());
        movieMapper.updatedEntityForm(movieForm, movie, director);
        movieRepository.save(movie);
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

    public void deleteById(@PathVariable Integer id) {
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundException(
                    "No such a movie with id: " + id
            );
        }
        movieRepository.deleteById(id);
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


    public Page<Movie> searchPaginated(
            String title,
            String genre,
            int page,
            int size,
            Sort sort
    ) {
        Pageable pageable = PageRequest.of(page, size, sort);
        String safeTitle = (title == null) ? "" : title;
        String safeGenre = (genre == null) ? "" : genre;

        return movieRepository.findByTitleContainingIgnoreCaseAndGenreContainingIgnoreCase(
                safeTitle,
                safeGenre,
                pageable
        );
    }

}
