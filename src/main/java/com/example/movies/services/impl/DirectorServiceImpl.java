package com.example.movies.services.impl;

import com.example.movies.models.Director;
import com.example.movies.repositories.DirectorRepository;
import com.example.movies.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;


    @Override
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public void deleteDirector(Director director) {
        directorRepository.delete(director);
    }

    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    public Director getDirectorByName(String name) {
        return directorRepository.findByName(name);
    }

    @Override
    public Optional<Director> getDirectorById(Long id) {
        return directorRepository.findById(id);
    }
}
