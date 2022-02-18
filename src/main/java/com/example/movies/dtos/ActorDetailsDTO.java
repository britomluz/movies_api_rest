package com.example.movies.dtos;
import com.example.movies.models.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActorDetailsDTO {

    private Long id;
    private String image;
    private String name;
    private String gender;
    private String age;
    private Set<MovieDTO> movies;

}
