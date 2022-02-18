package com.example.movies;

import com.example.movies.models.*;
import com.example.movies.models.Actor;
import com.example.movies.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public CommandLineRunner initData(UserService userService, ActorService actorService, MovieService movieService, GenderService genderService, DirectorService directorService){
		return (args) -> {
			//roles
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			Role manager = new Role(null, "ROLE_MANAGER");
			//userService.saveRole(new Role(null, "ROLE_MANAGER", new ArrayList<>()));

			//genres
			Gender ficcion = new Gender(null, "FICCION", null);
			Gender fantasia = new Gender(null, "FANTASIA", null);
			Gender infantil = new Gender(null, "INFANTIL", null);
			Gender comedia = new Gender(null, "COMEDIA", null);
			Gender romantica = new Gender(null,"ROMANTICA", null);
			Gender terror = new Gender(null, "TERROR", null);

			//users
			userService.saveUser(new User(null, "Luz", "Brito", "britomluz@gmail.com", "luz123" , new ArrayList<>()));
			userService.saveUser(new User(null, "Marcos", "Aguirre", "marcosa@gmail.com", "marcos123", new ArrayList<>()));
			User melba = new User(null, "Melba", "Brito", "melba@gmail.com", "melba123", new ArrayList<>());

			//actors
			Actor actor1 = new Actor(null, new URL("https://www.themoviedb.org/person/1136406-tom-holland#"), "TOM HOLLAND", GenderType.MALE, 25, null);
			Actor actor2 = new Actor(null, new URL("https://www.themoviedb.org/person/3223-robert-downey-jr#"), "ROBERT DOWNEY", GenderType.MALE, 56, null);
			Actor actor3 = new Actor(null, new URL("https://www.themoviedb.org/person/1245-scarlett-johansson#"), "SCARLETT JOHANSSON", GenderType.FEMALE, 37, null);
			Actor actor4 = new Actor(null, new URL("https://www.themoviedb.org/person/505710-zendaya#"), "ZENDAYA COLEMAN", GenderType.FEMALE, 25, null);
			Actor actor5 = new Actor(null, new URL("https://www.themoviedb.org/person/71580-benedict-cumberbatch#"), "BENEDICT CUMBERBATCH", GenderType.MALE, 47, null);
			Actor actor6 = new Actor(null, new URL("https://www.themoviedb.org/person/112-cate-blanchett#"), "CATE BLANCHETT", GenderType.FEMALE, 52, null);
			Actor actor7 = new Actor(null, new URL("https://www.themoviedb.org/person/80112-sylvester-mccoy#"), "SYLVESTER MCCOY", GenderType.MALE, 78, null);
			Actor actor8 = new Actor(null, new URL("https://www.themoviedb.org/person/287-brad-pitt#"), "BRAD PITT", GenderType.MALE, 58, null);
			Actor actor9 = new Actor(null, new URL("https://www.themoviedb.org/person/968367-stephanie-beatriz#"), "STEPHANIE BEATRIZ", GenderType.FEMALE, 41, null);
			Actor actor10 = new Actor(null, new URL("https://www.themoviedb.org/person/6384-keanu-reeves#"), "KEANU REEVES", GenderType.MALE, 57, null);
			Actor actor11 = new Actor(null, new URL("https://www.themoviedb.org/person/17178-patrick-wilson#"), "PATRICK WILSON", GenderType.MALE, 48, null);
			Actor actor12 = new Actor(null, new URL("https://www.themoviedb.org/person/16866-jennifer-lopez#"), "JENNIFER LOPEZ", GenderType.FEMALE, 52, null);
			Actor actor13 = new Actor(null, new URL("https://www.themoviedb.org/person/887-owen-wilson#"), "OWEN WILSON", GenderType.MALE, 53, null);
			Actor actor14 = new Actor(null, new URL("https://www.themoviedb.org/person/11288-robert-pattinson#"), "ROBERT PATTINSON", GenderType.MALE, 35, null);
			Actor actor15 = new Actor(null, new URL("https://www.themoviedb.org/person/37917-kristen-stewart#"), "KRISTEN STEWART", GenderType.FEMALE, 31, null);
			Actor actor16 = new Actor(null, new URL("https://www.themoviedb.org/person/98285-bella-thorne#"), "BELLA THORNE", GenderType.FEMALE, 24, null);
			Actor actor17 = new Actor(null, new URL("https://www.themoviedb.org/person/11662-jason-lee#"), "JASON LEE", GenderType.MALE, 51, null);
			Actor actor18 = new Actor(null, new URL("https://www.themoviedb.org/person/136347-jessica-mcnamee#"), "JESSICA MCNAMEE", GenderType.FEMALE, 35, null);
			Actor actor19 = new Actor(null, new URL("https://www.themoviedb.org/person/18918-dwayne-johnson#"), "DWAYNE JHONSON", GenderType.MALE, 49, null);
			Actor actor20 = new Actor(null, new URL("https://www.themoviedb.org/person/84214-taylor-lautner#"), "TAYLOR LAUTNER", GenderType.MALE, 30, null);
			Actor actor21 = new Actor(null, new URL("https://www.themoviedb.org/person/10859-ryan-reynolds#"), "RYAN REINOLDS", GenderType.MALE, 45, null);


			melba.getRoles().add(manager);

			//directors
			Director director1 = new Director(null,  "ANTHONY RUSSO",  null);
			Director director2 = new Director(null,  "JHON WATTS",  null);
			Director director3 = new Director(null,  "PETER JACKSON",  null);
			Director director4 = new Director(null,  "DAVID FINCHER",  null);
			Director director5 = new Director(null,  "JARED BUSH",  null);
			Director director6 = new Director(null,  "LANA Y LILY WACHOWSKI",  null);
			Director director7 = new Director(null,  "CHAD STAHELSKI",  null);
			Director director8 = new Director(null,  "JAMES WAN",  null);
			Director director9 = new Director(null,  "KAT COIRO",  null);
			Director director10 = new Director(null,  "DAVID SLADE",  null);
			Director director11 = new Director(null,  "ELISA AMORUSO",  null);
			Director director12 = new Director(null,  "JANICE KARMAN",  null);
			Director director13 = new Director(null,  "GREG RUSSO",  null);
			Director director14 = new Director(null,  "RAWSON MARSHAL TURNER",  null);
			Director director15 = new Director(null,  "MATT LIEBERMAN",  null);
			Director director16 = new Director(null,  "CHARLES KINNANE",  null);

			//movies
			Movie movie1 = new Movie(null, new URL("https://www.themoviedb.org/movie/299536-avengers-infinity-war#"),"AVENGERS: INFINITY WARS","2h 29m", LocalDate.parse("2018-01-05"),4.2F,"As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos.", ficcion, director1,  null);
			Movie movie2 = new Movie(null, new URL("https://www.themoviedb.org/movie/634649-spider-man-no-way-home#"), "SPIDERMAN: NO WAY HOME","2h 28m", LocalDate.parse("2021-01-05"),  4.7F,"Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become .",ficcion,director2,  null);
			Movie movie3 = new Movie(null, new URL("https://www.themoviedb.org/movie/49051-the-hobbit-an-unexpected-journey#"), "THE HOBBIT: AN UNEXPECTED JOURNEY","2h 49m", LocalDate.parse("2012-01-05"), 3.9F,"Bilbo Baggins, a hobbit enjoying his quiet life, is swept into an epic quest by Gandalf the Grey and thirteen dwarves who seek to reclaim their mountain home .",fantasia,director3,  null);
			Movie movie4 = new Movie(null, new URL("https://www.themoviedb.org/movie/4922-the-curious-case-of-benjamin-button#"), "THE CURIOUS CASE OF BENJAMIN BUTTON","2h 46m", LocalDate.parse("2008-07-12"), 4.7F,"I was born under unusual circumstances. And so begins. The Curious Case of Benjamin Button, adapted from the 1920s story by F. ",ficcion, director4,  null);
			Movie movie5 = new Movie(null, new URL("https://www.themoviedb.org/movie/568124-encanto#"), "ENCANTO","1h 42m", LocalDate.parse("2021-01-05"), 4.8F,"The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto.",infantil,director5,  null);
			Movie movie6 = new Movie(null, new URL("https://www.themoviedb.org/movie/624860-the-matrix-resurrections#"), "THE MATRIX RESURRECTIONS","2h 28m", LocalDate.parse("2021-01-05"), 3.7F,"Plagued by strange memories, Neo's life takes an unexpected turn when he finds himself back inside the Matrix.", ficcion, director6,  null);
			Movie movie7 = new Movie(null, new URL("https://www.themoviedb.org/movie/245891-john-wick#"), "JHON WICK","1h 41m", LocalDate.parse("2014-11-03"), 3.7F,"Ex-hitman John Wick comes out of retirement to track down the gangsters that took everything from him.",ficcion,director7,  null);
			Movie movie8 = new Movie(null, new URL("https://www.themoviedb.org/movie/49018-insidious#"), "INSIDIOUS","1h 43m", LocalDate.parse("2010-01-05"), 4.3F,"A family discovers that dark spirits have invaded their home after their son inexplicably falls into an endless sleep. When they reach out to a professional for help.",terror,director8,  null);
			Movie movie9 = new Movie(null, new URL("https://www.themoviedb.org/movie/138843-the-conjuring#"), "THE CONJURING","1h 52m", LocalDate.parse("2013-09-05"), 4.5F,"Paranormal investigators Ed and Lorraine Warren work to help a family terrorized by a dark presence in their farmhouse. Forced to confront a powerful entity, the Warrens find themselves .",terror,director8,  null);
			Movie movie10 = new Movie(null, new URL("https://www.themoviedb.org/movie/615904-marry-me#"), "MARRY ME","1h 22m", LocalDate.parse("2021-05-09"), 3.7F,"Explores the possibilities of what might happen when a superstar marries an average Joe as a joke and discovers that perhaps there are no accidents.",romantica,director9,  null);
			Movie movie11 = new Movie(null, new URL("https://www.themoviedb.org/movie/24021-the-twilight-saga-eclipse#"), "THE TWILIGHT SAGA: ECLIPSE","2h 4m", LocalDate.parse("2010-01-05"), 4.2F,"Bella once again finds herself surrounded by danger as Seattle is ravaged by a string of mysterious killings and a malicious vampire continues her quest for revenge.",romantica,director10,  null);
			Movie movie12 = new Movie(null, new URL("https://www.themoviedb.org/movie/18239-the-twilight-saga-new-moon#"), "THE TWILIGHT SAGA: NEW MOON","2h 11m", LocalDate.parse("2013-07-02"), 4.9F,"Forks, Washington resident Bella Swan is reeling from the departure of her vampire love, Edward Cullen, and finds comfort in her friendship with Jacob Black, a werewolf.",romantica,director10,  null);
			Movie movie13 = new Movie(null, new URL("https://www.themoviedb.org/movie/763148-time-is-up#"), "TIME IS UP","1h 48m", LocalDate.parse("2021-01-05"), 4.1F,"Vivien, an accomplished student with a passion for physics, and Roy, a troubled young man, are involved in an accident that forces them to reclaim their lives one minute at the time.",romantica,director11,  null);
			Movie movie14 = new Movie(null, new URL("https://www.themoviedb.org/movie/258509-alvin-and-the-chipmunks-4#"), "ALVIN AND THE CHIPMUNKS: THE ROAD CHIP","1h 32m", LocalDate.parse("2014-01-05"), 4.6F,"Through a series of misunderstandings, Alvin, Simon and Theodore come to believe that Dave is going to propose to his new girlfriend in New York City - and dump them. ",infantil,director12,  null);
			Movie movie15 = new Movie(null, new URL("https://www.themoviedb.org/movie/460465-mortal-kombat#"), "MORTAL KOMBAT","1h 50m", LocalDate.parse("2010-01-05"), 4.5F,"Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares.",fantasia,director13,  null);
			Movie movie16 = new Movie(null, new URL("https://www.themoviedb.org/movie/512195-red-notice#"), "RED NOTICE","1h 57m", LocalDate.parse("2021-01-05"), 3.9F,"An Interpol-issued Red Notice is a global alert to hunt and capture the world's most wanted. But when a daring heist brings together the FBI's top profiler and two rival criminals.",comedia,director14,  null);
			Movie movie17 = new Movie(null, new URL("https://www.themoviedb.org/movie/550988-free-guy#"), "FREE GUY","1h 55m", LocalDate.parse("2021-03-01"), 4.2F,"A bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline.",comedia,director15,  null);
			Movie movie18 = new Movie(null, new URL("https://www.themoviedb.org/movie/817648-home-team#"), "HOME TEAM","1h 35m", LocalDate.parse("2021-01-05"), 3.5F,"Two years after a Super Bowl win when NFL head coach Sean Payton is suspended, he goes back to his hometown and finds himself reconnecting with his 12-year-old son by coaching his Pop Warner football team.",comedia,director16,  null);

			//save entitys
			userService.saveRole(manager);
			userService.saveUser(melba);

			genderService.saveGenre(ficcion);
            genderService.saveGenre(infantil);
			genderService.saveGenre(romantica);
            genderService.saveGenre(comedia);
			genderService.saveGenre(fantasia);
            genderService.saveGenre(terror);

			actorService.saveActor(actor1);actorService.saveActor(actor2);
			actorService.saveActor(actor3);actorService.saveActor(actor4);
			actorService.saveActor(actor5);actorService.saveActor(actor6);
			actorService.saveActor(actor7);actorService.saveActor(actor8);
			actorService.saveActor(actor9);actorService.saveActor(actor10);
			actorService.saveActor(actor11);actorService.saveActor(actor12);
			actorService.saveActor(actor13);actorService.saveActor(actor14);
			actorService.saveActor(actor15);actorService.saveActor(actor16);
			actorService.saveActor(actor17);actorService.saveActor(actor18);
			actorService.saveActor(actor19);actorService.saveActor(actor20);
			actorService.saveActor(actor21);

			directorService.saveDirector(director1);directorService.saveDirector(director2);
			directorService.saveDirector(director3);directorService.saveDirector(director4);
			directorService.saveDirector(director5);directorService.saveDirector(director6);
			directorService.saveDirector(director7);directorService.saveDirector(director8);
			directorService.saveDirector(director9);directorService.saveDirector(director10);
			directorService.saveDirector(director11);directorService.saveDirector(director12);
			directorService.saveDirector(director13);directorService.saveDirector(director14);
			directorService.saveDirector(director15);directorService.saveDirector(director16);

			movieService.saveMovie(movie1);movieService.saveMovie(movie2);
			movieService.saveMovie(movie3);movieService.saveMovie(movie4);
			movieService.saveMovie(movie5);movieService.saveMovie(movie6);
			movieService.saveMovie(movie7);movieService.saveMovie(movie8);
			movieService.saveMovie(movie9);movieService.saveMovie(movie10);
			movieService.saveMovie(movie11);movieService.saveMovie(movie12);
			movieService.saveMovie(movie13);movieService.saveMovie(movie14);
			movieService.saveMovie(movie15);movieService.saveMovie(movie16);
			movieService.saveMovie(movie17);movieService.saveMovie(movie18);

			// add characters to movies
			actorService.addActor("TOM HOLLAND","AVENGERS: INFINITY WARS");
			actorService.addActor("ROBERT DOWNEY","AVENGERS: INFINITY WARS");
			actorService.addActor("SCARLETT JOHANSSON","AVENGERS: INFINITY WARS");
			actorService.addActor("TOM HOLLAND","SPIDERMAN: NO WAY HOME");
			actorService.addActor("ZENDAYA COLEMAN","SPIDERMAN: NO WAY HOME");
			actorService.addActor("BENEDICT CUMBERBATCH","SPIDERMAN: NO WAY HOME");
			actorService.addActor("BENEDICT CUMBERBATCH","THE HOBBIT: AN UNEXPECTED JOURNEY");
			actorService.addActor("SYLVESTER MCCOY","THE HOBBIT: AN UNEXPECTED JOURNEY");
			actorService.addActor("CATE BLANCHETT","THE HOBBIT: AN UNEXPECTED JOURNEY");
			actorService.addActor("CATE BLANCHETT","THE CURIOUS CASE OF BENJAMIN BUTTON");
			actorService.addActor("BRAD PITT","THE CURIOUS CASE OF BENJAMIN BUTTON");
			actorService.addActor("STEPHANIE BEATRIZ","ENCANTO");
			actorService.addActor("KEANU REEVES","THE MATRIX RESURRECTIONS");
			actorService.addActor("KEANU REEVES","JHON WICK");
			actorService.addActor("PATRICK WILSON","INSIDIOUS");
			actorService.addActor("PATRICK WILSON","THE CONJURING");
			actorService.addActor("OWEN WILSON","MARRY ME");
			actorService.addActor("ROBERT PATTINSON","THE TWILIGHT SAGA: ECLIPSE");
			actorService.addActor("KRISTEN STEWART","THE TWILIGHT SAGA: ECLIPSE");
			actorService.addActor("TAYLOR LAUTNER","THE TWILIGHT SAGA: ECLIPSE");
			actorService.addActor("ROBERT PATTINSON","THE TWILIGHT SAGA: NEW MOON");
			actorService.addActor("KRISTEN STEWART","THE TWILIGHT SAGA: NEW MOON");
			actorService.addActor("TAYLOR LAUTNER","THE TWILIGHT SAGA: NEW MOON");
			actorService.addActor("BELLA THORNE","TIME IS UP");
			actorService.addActor("JASON LEE","ALVIN AND THE CHIPMUNKS: THE ROAD CHIP");
			actorService.addActor("JESSICA MCNAMEE","MORTAL KOMBAT");
			actorService.addActor("DWAYNE JHONSON","RED NOTICE");
			actorService.addActor("TAYLOR LAUTNER","FREE GUY");
			actorService.addActor("RYAN REINOLDS","HOME TEAM");

			// add movies to characters
			movieService.addMovie("TOM HOLLAND","AVENGERS: INFINITY WARS");
			movieService.addMovie("ROBERT DOWNEY","AVENGERS: INFINITY WARS");
			movieService.addMovie("SCARLETT JOHANSSON","AVENGERS: INFINITY WARS");
			movieService.addMovie("TOM HOLLAND","SPIDERMAN: NO WAY HOME");
			movieService.addMovie("ZENDAYA COLEMAN","SPIDERMAN: NO WAY HOME");
			movieService.addMovie("BENEDICT CUMBERBATCH","SPIDERMAN: NO WAY HOME");
			movieService.addMovie("BENEDICT CUMBERBATCH","THE HOBBIT: AN UNEXPECTED JOURNEY");
			movieService.addMovie("SYLVESTER MCCOY","THE HOBBIT: AN UNEXPECTED JOURNEY");
			movieService.addMovie("CATE BLANCHETT","THE HOBBIT: AN UNEXPECTED JOURNEY");
			movieService.addMovie("CATE BLANCHETT","THE CURIOUS CASE OF BENJAMIN BUTTON");
			movieService.addMovie("BRAD PITT","THE CURIOUS CASE OF BENJAMIN BUTTON");
			movieService.addMovie("STEPHANIE BEATRIZ","ENCANTO");
			movieService.addMovie("KEANU REEVES","THE MATRIX RESURRECTIONS");
			movieService.addMovie("KEANU REEVES","JHON WICK");
			movieService.addMovie("PATRICK WILSON","INSIDIOUS");
			movieService.addMovie("PATRICK WILSON","THE CONJURING");
			movieService.addMovie("OWEN WILSON","MARRY ME");
			movieService.addMovie("ROBERT PATTINSON","THE TWILIGHT SAGA: ECLIPSE");
			movieService.addMovie("KRISTEN STEWART","THE TWILIGHT SAGA: ECLIPSE");
			movieService.addMovie("TAYLOR LAUTNER","THE TWILIGHT SAGA: ECLIPSE");
			movieService.addMovie("ROBERT PATTINSON","THE TWILIGHT SAGA: NEW MOON");
			movieService.addMovie("KRISTEN STEWART","THE TWILIGHT SAGA: NEW MOON");
			movieService.addMovie("TAYLOR LAUTNER","THE TWILIGHT SAGA: NEW MOON");
			movieService.addMovie("BELLA THORNE","TIME IS UP");
			movieService.addMovie("JASON LEE","ALVIN AND THE CHIPMUNKS: THE ROAD CHIP");
			movieService.addMovie("JESSICA MCNAMEE","MORTAL KOMBAT");
			movieService.addMovie("DWAYNE JHONSON","RED NOTICE");
			movieService.addMovie("TAYLOR LAUTNER","FREE GUY");
			movieService.addMovie("RYAN REINOLDS","HOME TEAM");


			//role to users
			userService.addRoleToUser("marcosa@gmail.com", "ROLE_USER");
			userService.addRoleToUser("britomluz@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUser("britomluz@gmail.com", "ROLE_USER");
			//userService.addRoleToUser("jhonc@gmail.com", "ROLE_MANAGER");

		};

	}
}
