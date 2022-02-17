package com.example.movies.controllers;
import com.example.movies.dtos.ActorDTO;
import com.example.movies.dtos.ActorDetailsDTO;
import com.example.movies.dtos.MovieDTO;
import com.example.movies.models.Actor;
import com.example.movies.models.Role;
import com.example.movies.models.User;
import com.example.movies.services.ActorService;
import com.example.movies.services.MovieService;
import com.example.movies.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
public class ActorController {
    @Autowired
    private ActorService actorService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    //get actors list
    @GetMapping("/actors")
    public ResponseEntity<Object> getActors(@RequestParam MultiValueMap<String, String> queryMap,
                                                @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        try{

            ActorDetailsDTO filterActor = ActorDetailsDTO.builder()
                    .name(queryMap.getFirst("name"))
                    .age(queryMap.getFirst("age") != null ? Integer.parseInt(Objects.requireNonNull(queryMap.getFirst("age"))):null)
                    .build();

            Page<Actor> page = actorService.getActorByFilter(filterActor, pageable);

            Map<String, Object> actors = new HashMap<>();
            actors.put("total", page.getTotalElements());
            actors.put("pages", page.getTotalPages());
            actors.put("numberPage", page.getNumber());
            actors.put("size", page.getSize());
            actors.put("sort", page.getSort());
            actors.put("content", page.getContent().stream().map(actor ->
                    ActorDTO.builder()
                            .id(actor.getId())
                            .image(actor.getImage())
                            .name(actor.getName())
                            .build()));

            return ResponseEntity.ok(actors);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
        }
    }

    //get specific actor
    @GetMapping("/actors/{id}")
    public ResponseEntity<Object> getActor(@PathVariable Long id){

        Actor actor = actorService.getActorById(id).orElse(null);

        if (actor == null){
            return new ResponseEntity<>("Actor doesn't exists",HttpStatus.NOT_FOUND);
        }
        ActorDetailsDTO actorDTO = ActorDetailsDTO.builder()
                .id(actor.getId())
                .image(actor.getImage())
                .name(actor.getName())
                .age(actor.getAge())
                .movies(actor.getMovies().stream().map(movie -> MovieDTO.builder().id(movie.getId()).image(movie.getImage().toString()).title(movie.getTitle()).year(movie.getYear().toString()).gender(movie.getGender().getName()).build()).collect(Collectors.toSet()))
                .build();
        return ResponseEntity.ok().body(actorDTO);
    }

    //add and actor
    @PostMapping("/actors")
    public ResponseEntity<?> createActor(Authentication authentication, @RequestBody Actor actor){
        User user = userService.getUser(authentication.getName());
        Role role = userService.getRole("ROLE_ADMIN");

        if (!user.getRoles().contains(role)){
            return new ResponseEntity<>("Not allowed", HttpStatus.METHOD_NOT_ALLOWED);
        }


        String age = String.valueOf(actor.getAge());
        String image = String.valueOf(actor.getImage());
        String gender = String.valueOf(actor.getGender());

        if (actor.getName().isEmpty() || age.isEmpty() || image.isEmpty() || gender.isEmpty()){
            return new ResponseEntity<>("Empty fields",HttpStatus.BAD_REQUEST);
        }

        Actor actorByName = actorService.getActorByName(actor.getName());

        if (actorByName != null){
            return new ResponseEntity<>("Actor already exists",HttpStatus.CONFLICT);
        }

        Actor newActor = new Actor(null, actor.getImage(), actor.getName().toUpperCase(), actor.getGender(), actor.getAge(), null);

        actorService.saveActor(newActor);

        return new ResponseEntity<>("Actor created",HttpStatus.CREATED);
    }

    //edit actor information
    @PutMapping("/actors/{id}")
    public ResponseEntity<?> editActor(Authentication authentication, @PathVariable("id") Long id,
                                         @RequestBody Actor actor){
        User user = userService.getUser(authentication.getName());
        Role role = userService.getRole("ROLE_ADMIN");

        if (!user.getRoles().contains(role)){
            return new ResponseEntity<>("Not allowed", HttpStatus.METHOD_NOT_ALLOWED);
        }

        String age = String.valueOf(actor.getAge());
        String image = String.valueOf(actor.getImage());
        String gender = String.valueOf(actor.getGender());

        if (actor.getName().isEmpty() || age.isEmpty() || image.isEmpty() || gender.isEmpty()){
            return new ResponseEntity<>("Empty fields",HttpStatus.BAD_REQUEST);
        }

        Actor currentActor = actorService.getActorById(id).orElse(null);

        if(currentActor == null){
            return new ResponseEntity<>("Actor doesn't exists", HttpStatus.NOT_FOUND);
        }

        currentActor.setImage(actor.getImage());
        currentActor.setName(actor.getName());
        currentActor.setGender(actor.getGender());
        currentActor.setAge(actor.getAge());

        actorService.saveActor(currentActor);

        return new ResponseEntity<>("Actor edited",HttpStatus.OK);
    }

    //add actor to movie
    @PostMapping("/actors/movies")
    public ResponseEntity<Object> addActorToMovie(@RequestBody CharacterToMovieForm form){

        actorService.addActor(form.getActor(), form.getMovie());
        return ResponseEntity.ok().build();
    }

    @Data
    class CharacterToMovieForm{
        private String actor;
        private  String movie;
    }

}
