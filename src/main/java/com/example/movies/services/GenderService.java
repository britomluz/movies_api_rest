package com.example.movies.services;

import com.example.movies.models.Gender;

import java.util.List;
import java.util.Optional;

public interface GenderService {

    public Gender saveGenre(Gender gender);
    public void deleteGenre(Gender gender);
    public List<Gender> getAllGenres();
    public Gender getGenreByName(String name);
    public Optional <Gender> getGenreById(Long id);

}
