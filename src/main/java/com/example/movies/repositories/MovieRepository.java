package com.example.movies.repositories;

import com.example.movies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MovieRepository  extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
    Movie findByTitle(String movie);
}

