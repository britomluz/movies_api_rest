package com.example.movies.controllers;

import com.example.movies.dtos.DirectorDTO;
import com.example.movies.dtos.DirectorDetailsDTO;
import com.example.movies.dtos.MovieDTO;
import com.example.movies.models.Director;
import com.example.movies.models.Role;
import com.example.movies.models.User;
import com.example.movies.services.DirectorService;
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
public class DirectorController {

    @Autowired
    private UserService userService;

    @Autowired
    private DirectorService directorService;


    @GetMapping("/directors")
    public ResponseEntity<Object> getDirector(){

        return ResponseEntity.ok().body(directorService.getAllDirectors());
    }

    //get specific director
    @GetMapping("/directors/{id}")
    public ResponseEntity<Object> getDirector(@PathVariable Long id){

        Director director = directorService.getDirectorById(id).orElse(null);

        if (director == null){
            return new ResponseEntity<>("Director doesn't exists", HttpStatus.NOT_FOUND);
        }

        DirectorDetailsDTO directorDTO = DirectorDetailsDTO.builder()
                .id(director.getId())
                .name(director.getName())
                .movies(director.getMovies().stream().map(movie -> MovieDTO.builder().id(movie.getId()).image(movie.getImage().toString()).title(movie.getTitle()).year(movie.getYear().toString()).gender(movie.getGender().getName()).build()).collect(Collectors.toSet()))
                .build();

        return ResponseEntity.ok().body(directorDTO);
    }

    //add new director
    @PostMapping("/directors")
    public ResponseEntity<?> createDirector(Authentication authentication, @RequestBody Director director){
        User user = userService.getUser(authentication.getName());
        Role role = userService.getRole("ROLE_ADMIN");

        if (!user.getRoles().contains(role)){
            return new ResponseEntity<>("Not allowed", HttpStatus.METHOD_NOT_ALLOWED);
        }

        if (director.getName().isEmpty()){
            return new ResponseEntity<>("Empty fields",HttpStatus.BAD_REQUEST);
        }

        Director directorByName = directorService.getDirectorByName(director.getName());

        if (directorByName != null){
            return new ResponseEntity<>("Director already exists",HttpStatus.CONFLICT);
        }

        Director newDirector = new Director(null, director.getName().toUpperCase(), new ArrayList<>());

        directorService.saveDirector(newDirector);

        return new ResponseEntity<>("Director created",HttpStatus.CREATED);
    }

}
