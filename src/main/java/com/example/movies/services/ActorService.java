package com.example.movies.services;



import com.example.movies.dtos.ActorDetailsDTO;
import com.example.movies.models.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    public Actor saveActor(Actor actor);
    public void addActor(String actor, String movie);
    public Actor getActorByName(String name);
    public Optional<Actor> getActorById(Long id);
    public void deleteActor(Actor actor);
    public List<Actor> getAllActors();
    public Page<Actor> getActorByFilter(ActorDetailsDTO filter, Pageable pageable);

}
