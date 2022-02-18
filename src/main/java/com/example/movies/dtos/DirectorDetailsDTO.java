package com.example.movies.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DirectorDetailsDTO {

    private Long id;
    private String name;
    private Set<MovieDTO> movies;

}
