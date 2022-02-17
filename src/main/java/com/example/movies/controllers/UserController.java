package com.example.movies.controllers;
import com.example.movies.models.Role;
import com.example.movies.models.User;
import com.example.movies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/users")
    public ResponseEntity<Object> getUsers(){

        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestParam String firstName, @RequestParam String lastName,
                                           @RequestParam String username, @RequestParam String password){

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Empty fields", HttpStatus.BAD_REQUEST);
        }

        if (userService.getUser(username) != null) {
            return new ResponseEntity<>("User already exists. Try it again", HttpStatus.CONFLICT);
        }

        User user = new User(null, firstName, lastName, username, password, new ArrayList<>());

        userService.saveUser(user);
        userService.addRoleToUser(user.getUsername(), "ROLE_USER");

        return new ResponseEntity<>("User created",HttpStatus.CREATED);
    }

    @PostMapping("/roles")
    public ResponseEntity<Object> saveRole(@RequestBody Role role){

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());

        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }


}
