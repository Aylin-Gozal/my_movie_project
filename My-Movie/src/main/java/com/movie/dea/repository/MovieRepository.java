package com.movie.dea.repository;

import com.movie.dea.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query(value = "SELECT * FROM movies WHERE LOWER(title) LIKE LOWER(CONCAT('%', :title, '%'))",nativeQuery = true)
    List<Movie> findByTitle(String title);

    @Query(value = "SELECT * FROM movies WHERE LOWER(genre) LIKE LOWER(CONCAT('%', :genre, '%'))",nativeQuery = true)
    List<Movie> findByGenre(String genre);

    @Query(value = "SELECT * FROM movies WHERE rating >= :minRating", nativeQuery = true)
    List<Movie> findByMinRating(Double minRating);

    @Query(value = "SELECT * FROM movies WHERE release_date >= :releaseDate", nativeQuery = true)
    List<Movie> findByReleaseDate(LocalDate releaseDate);


    Page<Movie>findAll(Pageable pageable);

}
