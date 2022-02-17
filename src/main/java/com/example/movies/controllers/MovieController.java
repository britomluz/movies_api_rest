package com.example.movies.controllers;
import com.example.movies.dtos.ActorDTO;
import com.example.movies.dtos.MovieDTO;
import com.example.movies.dtos.MovieDetailsDTO;
import com.example.movies.models.*;
import com.example.movies.repositories.MovieRepository;
import com.example.movies.services.ActorService;
import com.example.movies.services.DirectorService;
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
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
public class MovieController {
    @Autowired
    private ActorService actorService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DirectorService directorService;

    //get movie list
    @GetMapping("/movies")
    public ResponseEntity<Object> getMovies(@RequestParam MultiValueMap<String, String> queryMap,
                                                @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        try{

            MovieDTO filterMovie = MovieDTO.builder()
                    .title(queryMap.getFirst("title"))
                    .year(queryMap.getFirst("year"))
                    .gender(queryMap.getFirst("gender"))
                    //.gender(Gender.builder().name(queryMap.getFirst("gender")).build())
                    .build();

            Page<Movie> page = movieService.getMovieByFilter(filterMovie, pageable);

            Map<String, Object> movies = new HashMap<>();
            movies.put("total", page.getTotalElements());
            movies.put("pages", page.getTotalPages());
            movies.put("numberPage", page.getNumber());
            movies.put("size", page.getSize());
            movies.put("sort", page.getSort());
            movies.put("content", page.getContent().stream().map(movie ->
                    MovieDTO.builder()
                            .id(movie.getId())
                            .image(movie.getImage().toString())
                            .title(movie.getTitle())
                            .gender(movie.getGender().getName())
                            .year(movie.getYear().toString())
                            .build()));

            return ResponseEntity.ok().body(movies);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
        }

    }
    //get a specific movie
    @GetMapping("/movies/{id}")
    public ResponseEntity<Object> getMovie(@PathVariable Long id){

        Movie movie = movieService.getMovieById(id).orElse(null);

        if (movie == null){
            return new ResponseEntity<>("Movie doesn't exists",HttpStatus.NOT_FOUND);
        }

        MovieDetailsDTO movieDTO = MovieDetailsDTO.builder()
                .id(movie.getId())
                .image(movie.getImage().toString())
                .title(movie.getTitle())
                .duration(movie.getDuration())
                .gender(movie.getGender().getName())
                .director(movie.getDirector().getName())
                .year(movie.getYear().toString())
                .rating(movie.getRating().toString())
                .overview(movie.getOverview())
                .actors(movie.getActors().stream().map(actor -> ActorDTO.builder().id(actor.getId()).image(actor.getImage()).name(actor.getName()).build()).collect(Collectors.toSet()))
                .build();

        return ResponseEntity.ok().body(movieDTO);
    }

    //add new movie
    @PostMapping("/movies")
    public ResponseEntity<?> createMovie(Authentication authentication, @RequestBody Movie movie){
        User user = userService.getUser(authentication.getName());
        Role role = userService.getRole("ROLE_ADMIN");

        if (!user.getRoles().contains(role)){
            return new ResponseEntity<>("Not allowed", HttpStatus.METHOD_NOT_ALLOWED);
        }

        String rating = String.valueOf(movie.getRating());
        String image = String.valueOf(movie.getImage());

        if (movie.getTitle().isEmpty() || image.isEmpty() || rating.isEmpty()){
            return new ResponseEntity<>("Empty fields",HttpStatus.BAD_REQUEST);
        }

        if(movie.getRating() < 1 && movie.getRating() > 5){
            return new ResponseEntity<>("Invalid rate", HttpStatus.BAD_REQUEST);
        }

        if(movie.getYear().isAfter(LocalDate.now())){
            return new ResponseEntity<>("Invalid date", HttpStatus.BAD_REQUEST);
        }

        Movie movie1 = movieService.getMovieByTitle(movie.getTitle());
        if (movie1 != null){
            return new ResponseEntity<>("Movie already exists",HttpStatus.CONFLICT);
        }

        Movie newMovie = new Movie(null, movie.getImage(), movie.getTitle(), movie.getDuration(), movie.getYear(), movie.getRating(),movie.getOverview(),movie.getGender(), movie.getDirector(), new HashSet<>());
        movieService.saveMovie(newMovie);

        return new ResponseEntity<>("Actor created",HttpStatus.CREATED);
    }

    //edit movie
    @PutMapping("/movies/{id}")
    public ResponseEntity<?> editMovie(Authentication authentication, @PathVariable("id") Long id,
                                           @RequestBody Movie movie){
        User user = userService.getUser(authentication.getName());
        Role role = userService.getRole("ROLE_ADMIN");

        if (!user.getRoles().contains(role)){
            return new ResponseEntity<>("Not allowed", HttpStatus.METHOD_NOT_ALLOWED);
        }

        String rating = String.valueOf(movie.getRating());
        String image = String.valueOf(movie.getImage());

        if (movie.getTitle().isEmpty() || image.isEmpty() || rating.isEmpty()){
            return new ResponseEntity<>("Empty fields",HttpStatus.BAD_REQUEST);
        }

        if(movie.getYear().isAfter(LocalDate.now())){
            return new ResponseEntity<>("Invalid date", HttpStatus.BAD_REQUEST);
        }

        Movie movieById = movieService.getMovieById(id).orElse(null);

        if(movieById == null){
            return new ResponseEntity<>("Movie doesn't exists", HttpStatus.NOT_FOUND);
        }

        movieById.setImage(movieById.getImage());
        movieById.setTitle(movieById.getTitle());
        movieById.setDuration(movieById.getDuration());
        movieById.setYear(movieById.getYear());
        movieById.setRating(movieById.getRating());
        movieById.setOverview(movieById.getOverview());

        movieService.saveMovie(movieById);

        return new ResponseEntity<>("Movie edited",HttpStatus.OK);
    }

    //delete specific movie
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(Authentication authentication, @PathVariable("id") Long id){

        User user = userService.getUser(authentication.getName());

        Role role = userService.getRole("ROLE_ADMIN");
        if (!user.getRoles().contains(role)){
            return new ResponseEntity<>("Not allowed", HttpStatus.METHOD_NOT_ALLOWED);
        }

        Movie movie = movieService.getMovieById(id).orElse(null);

        if(movie == null){
            return new ResponseEntity<>("Movie doesn't exists", HttpStatus.BAD_REQUEST);
        }

        Set<Actor> actors = movie.getActors();

        actors.forEach(actor -> {
            List<Movie> movie1 = actor.getMovies().stream().filter(movie2 -> movie2.getId().equals(id)).collect(Collectors.toList());
            movie1.remove(movie1.get(0));
            actor.setMovies(movie1);
            actorService.saveActor(actor);
        });

        movie.setActors(null);
        movie.setDirector(null);
        movie.setGender(null);

        movieRepository.save(movie);
        movieRepository.delete(movie);

        return new ResponseEntity<>("Movie deleted",HttpStatus.OK);
    }

    //add movie to actor (many to many)
    @PostMapping("/movies/actors")
    public ResponseEntity<Object> addMovieToActor(@RequestBody MovieToActorForm form){

        movieService.addMovie(form.getActor(), form.getMovie());
        return ResponseEntity.ok().build();
    }

    @Data
    class MovieToActorForm{
        private String actor;
        private  String movie;
    }
}
