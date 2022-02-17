package com.example.movies;

import com.example.movies.models.Actor;
import com.example.movies.models.GenderType;
import com.example.movies.models.Movie;
import com.example.movies.models.User;
import com.example.movies.services.ActorService;
import com.example.movies.services.MovieService;
import com.example.movies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoriesTest {

    @Autowired
    UserService userService;
    @Autowired
    ActorService actorService;
    @Autowired
    MovieService movieService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void authLoginOk() throws Exception{}

    @Test
    public void createUser() {
        User user = new User(null, "Jhon", "C", "jhonc@gmail.com", passwordEncoder.encode("melba123"), new ArrayList<>());
        userService.saveUser(user);

        List<User> users = userService.getUsers();
        assertThat(users).hasSize(4);

        User userByUsername = userService.getUser("jhonc@gmail.com");
        assertThat(userByUsername).isNotNull();
    }

    @Test
    public void addRoleToUser() {
        userService.addRoleToUser("jhonc@gmail.com", "ROLE_ADMIN");
    }

    @Test
    public void createdActor() throws MalformedURLException {

        Actor actor = new Actor(null, new URL("https://www.ecured.cu/images/thumb/8/8b/Harry-potter.jpg/260px-Harry-potter.jpg"), "HERMIONE", GenderType.FEMALE, 12, null);
        actorService.saveActor(actor);

        List<Actor> actors = actorService.getAllActors();

        System.out.println("Actor added");

        Actor actorByName = actorService.getActorByName("HERMIONE");
        assertThat(actorByName).isNotNull();
        assertThat(22).isEqualTo(actors.size());
    }

    @Test
    public void createdMovie() throws MalformedURLException {

        Movie movie = new Movie(null, new URL("https://pics.filmaffinity.com/Diario_de_una_pasi_n-565006977-large.jpg"), "HARRY POTTER Y LA CAMARA SECRETA","1h 57m", LocalDate.parse("2007-01-02"), 4.4F,"sdsaf", null , null,  null);
        movieService.saveMovie(movie);
        System.out.println("Movie added");

        List<Movie> movies = movieService.getAllMovies();
        assertThat(movies).hasSize(19);

        Movie movieByTitle = movieService.getMovieByTitle("HARRY POTTER Y LA CAMARA SECRETA");
        assertThat(movieByTitle).isNotNull();

    }

}
