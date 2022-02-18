package com.example.movies.services.impl;

import com.example.movies.dtos.ActorDTO;
import com.example.movies.dtos.ActorDetailsDTO;
import com.example.movies.models.Actor;
import com.example.movies.models.Movie;
import com.example.movies.repositories.ActorRepository;
import com.example.movies.repositories.MovieRepository;
import com.example.movies.services.ActorService;
import com.example.movies.services.specifications.ActorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public Actor saveActor(Actor actor) {

        return actorRepository.save(actor);
    }

    @Override
    public void addActor(String actorName, String movieName) {
        Actor actor = actorRepository.findByName(actorName);
        Movie movie = movieRepository.findByTitle(movieName);
        movie.getActors().add(actor);
    }

    @Override
    public Actor getActorByName(String name) {
        return actorRepository.findByName(name);
    }

    @Override
    public Optional<Actor> getActorById(Long id) {
        return actorRepository.findById(id);
    }

    @Override
    public void deleteActor(Actor actor) {
        actorRepository.delete(actor);
    }

    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public Page<Actor> getActorByFilter(ActorDTO filter, Pageable pageable) {

       return actorRepository.findAll(ActorSpecification.actorFilter(filter), pageable);
    }
}
