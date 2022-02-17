package com.example.movies.repositories;
import com.example.movies.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GenreRepository  extends JpaRepository<Gender, Long> {
    Gender findByName(String genreName);
}
