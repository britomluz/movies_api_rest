package com.example.movies.services;

import com.example.movies.dtos.MovieDTO;
import com.example.movies.models.Actor;
import com.example.movies.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieService {
    public Movie saveMovie(Movie movie);
    public void deleteMovie(Long id);
    public void addMovie(String actorName, String movieName);
    public Movie getMovieByTitle(String name);
    public Optional<Movie> getMovieById(Long id);
    public List<Movie> getAllMovies();
    public Page<Movie> getMovieByFilter(MovieDTO filter, Pageable pageable);
}
