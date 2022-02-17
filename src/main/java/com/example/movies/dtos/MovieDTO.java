package com.example.movies.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {

    private Long id;
    private String image;
    private String title;
    private String year;
    private String gender;

}
