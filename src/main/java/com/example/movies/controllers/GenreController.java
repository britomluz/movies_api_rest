package com.example.movies.controllers;

import com.example.movies.dtos.GenreDTO;
import com.example.movies.dtos.GenreDetailsDTO;
import com.example.movies.dtos.MovieDTO;
import com.example.movies.models.Gender;
import com.example.movies.models.Role;
import com.example.movies.models.User;
import com.example.movies.services.GenderService;
import com.example.movies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class GenreController {

    @Autowired
    GenderService genderService;

    @Autowired
    UserService userService;

    //get genres list
    @GetMapping("/genders")
    public ResponseEntity<Object> getGenres(){

        return ResponseEntity.ok().body(genderService.getAllGenres().stream().map(genre ->
                GenreDTO.builder()
                        .id(genre.getId())
                        .name(genre.getName())
                        .build())
                .collect(Collectors.toList()));
    }

    //get specific gender
    @GetMapping("/genders/{id}")
    public ResponseEntity<Object> getGenre(@PathVariable Long id){

        Gender gender = genderService.getGenreById(id).orElse(null);

        if (gender == null){
            return new ResponseEntity<>("Gender doesn't exists",HttpStatus.NOT_FOUND);
        }

        GenreDetailsDTO genreDTO = GenreDetailsDTO.builder()
                .id(gender.getId())
                .name(gender.getName())
                .movies(gender.getMovies().stream().map(movie -> MovieDTO.builder().id(movie.getId()).image(movie.getImage().toString()).title(movie.getTitle()).year(movie.getYear().toString()).gender(movie.getGender().getName()).build()).collect(Collectors.toList()))
                .build();

        return ResponseEntity.ok().body(genreDTO);
    }

    @PostMapping("/genders")
    public ResponseEntity<?> createActor(Authentication authentication, @RequestBody Gender gender){
        User user = userService.getUser(authentication.getName());
        Role role = userService.getRole("ROLE_ADMIN");

        if (!user.getRoles().contains(role)){
            return new ResponseEntity<>("Not allowed", HttpStatus.METHOD_NOT_ALLOWED);
        }

        if (gender.getName().isEmpty()){
            return new ResponseEntity<>("Empty fields",HttpStatus.BAD_REQUEST);
        }

        Gender genderByName = genderService.getGenreByName(gender.getName());

        if (genderByName != null){
            return new ResponseEntity<>("Gender already exists",HttpStatus.CONFLICT);
        }

        Gender newGender = new Gender(null, gender.getName().toUpperCase(), new ArrayList<>());

        genderService.saveGenre(newGender);

        return new ResponseEntity<>("Gender created",HttpStatus.CREATED);
    }

}
