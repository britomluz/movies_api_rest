package com.example.movies.services.impl;

import com.example.movies.models.Gender;
import com.example.movies.repositories.GenderRepository;
import com.example.movies.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;

    @Override
    public Gender saveGenre(Gender gender) {
        return genderRepository.save(gender);
    }

    @Override
    public void deleteGenre(Gender gender) {
        genderRepository.delete(gender);
    }

    @Override
    public List<Gender> getAllGenres() {
        return genderRepository.findAll();
    }

    @Override
    public Gender getGenreByName(String name) {
        return genderRepository.findByName(name);
    }

    @Override
    public Optional<Gender> getGenreById(Long id) {
        return genderRepository.findById(id);
    }



}
