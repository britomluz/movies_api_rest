package com.example.movies.services.impl;

import com.example.movies.dtos.MovieDTO;
import com.example.movies.models.Actor;
import com.example.movies.models.Movie;
import com.example.movies.repositories.ActorRepository;
import com.example.movies.repositories.MovieRepository;
import com.example.movies.services.MovieService;
import com.example.movies.services.specifications.MovieSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void addMovie(String actorName, String movieName) {
        Actor actor = actorRepository.findByName(actorName);
        Movie movie = movieRepository.findByTitle(movieName);
       // movie.getActors().add(actor);
        actor.getMovies().add(movie);
    }
    @Override
    public Movie getMovieByTitle(String name) {
        return movieRepository.findByTitle(name);
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Page<Movie> getMovieByFilter(MovieDTO filter, Pageable pageable) {
        return movieRepository.findAll(MovieSpecification.movieFilter(filter), pageable);
    }
}
