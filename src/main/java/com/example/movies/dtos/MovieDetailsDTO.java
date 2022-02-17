package com.example.movies.dtos;
import com.example.movies.models.Director;
import com.example.movies.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.time.LocalDate;
import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDetailsDTO {

    private Long id;
    private String image;
    private String title;
    private String duration;
    private String gender;
    private String director;
    private String year;
    private String rating;
    private String overview;
    private Set<ActorDTO> actors;

}
