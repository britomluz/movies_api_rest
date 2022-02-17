package com.example.movies.services.impl;

import com.example.movies.models.Gender;
import com.example.movies.repositories.GenreRepository;
import com.example.movies.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Gender saveGenre(Gender gender) {
        return genreRepository.save(gender);
    }

    @Override
    public void deleteGenre(Gender gender) {
        genreRepository.delete(gender);
    }

    @Override
    public List<Gender> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Gender getGenreByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public Optional<Gender> getGenreById(Long id) {
        return genreRepository.findById(id);
    }



}
