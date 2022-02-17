package com.example.movies.controllers;

import com.example.movies.dtos.GenreDTO;
import com.example.movies.dtos.GenreDetailsDTO;
import com.example.movies.dtos.MovieDTO;
import com.example.movies.models.Gender;
import com.example.movies.models.Role;
import com.example.movies.models.User;
import com.example.movies.services.GenreService;
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
    GenreService genreService;

    @Autowired
    UserService userService;

    //get genres list
    @GetMapping("/genders")
    public ResponseEntity<Object> getGenres(){

        return ResponseEntity.ok().body(genreService.getAllGenres().stream().map(genre ->
                GenreDTO.builder()
                        .id(genre.getId())
                        .name(genre.getName())
                        .build())
                .collect(Collectors.toList()));
    }

    //get specific gender
    @GetMapping("/genders/{id}")
    public ResponseEntity<Object> getGenre(@PathVariable Long id){

        Gender gender = genreService.getGenreById(id).orElse(null);

        if (gender == null){
            return new ResponseEntity<>("Gender doesn't exists",HttpStatus.NOT_FOUND);
        }

        GenreDetailsDTO genreDTO = GenreDetailsDTO.builder()
                .id(gender.getId())
                .name(gender.getName())
                .movies(gender.getMovies().stream().map(movie -> MovieDTO.builder().id(movie.getId()).image(movie.getImage().toString()).title(movie.getTitle()).year(movie.getYear().toString()).build()).collect(Collectors.toList()))
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

        Gender genderByName = genreService.getGenreByName(gender.getName());

        if (genderByName != null){
            return new ResponseEntity<>("Gender already exists",HttpStatus.CONFLICT);
        }

        Gender newGender = new Gender(null, gender.getName().toUpperCase(), new ArrayList<>());

        genreService.saveGenre(newGender);

        return new ResponseEntity<>("Gender created",HttpStatus.CREATED);
    }

}
