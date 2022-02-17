package com.example.movies.services.specifications;

import com.example.movies.dtos.MovieDTO;
import com.example.movies.models.Movie;
import com.example.movies.services.ActorService;
import com.example.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieSpecification {
    @Autowired
    MovieService movieService;

    @Autowired
    ActorService characterService;

    public static Specification<Movie> movieFilter(MovieDTO filter){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> criteriaList = new ArrayList<>();

            if(StringUtils.hasText(filter.getTitle())){
                criteriaList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), "%"+filter.getTitle().toUpperCase(Locale.ROOT)+"%"));
            }

            if(StringUtils.hasText(filter.getYear())){
                criteriaList.add(criteriaBuilder.like(root.get("premiere").as(String.class), "%"+filter.getYear()+"%"));
            }

             //   criteriaList.add(criteriaBuilder.equal(root.get("gender").get("name"), filter.getGender().getName()));

            if(StringUtils.hasText(filter.getGender())){
                criteriaList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("gender")), "%"+filter.getGender().toUpperCase(Locale.ROOT)+"%"));
            }

            return criteriaBuilder.and(criteriaList.toArray(new Predicate[criteriaList.size()]));
        };
    }
}
