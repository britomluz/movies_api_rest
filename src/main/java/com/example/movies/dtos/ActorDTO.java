package com.example.movies.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActorDTO {

    private Long id;
    private URL image;
    private String name;

}
