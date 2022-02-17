package com.example.movies.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreDetailsDTO {

    private Long id;
    private String name;
    private List<MovieDTO> movies;

}
