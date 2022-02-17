package com.example.movies.services;

import com.example.movies.models.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {

    public Director saveDirector(Director director);
    public void deleteDirector(Director director);
    public List<Director> getAllDirectors();
    public Director getDirectorByName(String name);
    public Optional<Director> getDirectorById(Long id);

}
