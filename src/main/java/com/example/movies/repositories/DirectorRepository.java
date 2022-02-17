package com.example.movies.repositories;

import com.example.movies.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DirectorRepository extends JpaRepository<Director, Long> {
    Director findByName(String directorName);
}
