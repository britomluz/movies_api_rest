package com.example.movies.services.specifications;

import com.example.movies.dtos.ActorDTO;
import com.example.movies.dtos.ActorDetailsDTO;
import com.example.movies.models.Actor;
import com.example.movies.services.ActorService;
import com.example.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActorSpecification {

    @Autowired
    MovieService movieService;

    @Autowired
    ActorService actorService;

    public static Specification<Actor> actorFilter(ActorDTO filter){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> criteriaList = new ArrayList<>();

            if(StringUtils.hasText(filter.getName())){
                criteriaList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%"+filter.getName().toUpperCase(Locale.ROOT)+"%"));
            }
            if(StringUtils.hasText(filter.getGender())){
                criteriaList.add(criteriaBuilder.like(root.get("gender").get("name").as(String.class), "%"+filter.getGender()+"%"));
            }
            /*
            if(StringUtils.hasText(filter.getAge())){
                criteriaList.add(criteriaBuilder.like(root.get("age").as(String.class), "%"+filter.getAge()+"%"));
            }*/

            return criteriaBuilder.and(criteriaList.toArray(new Predicate[criteriaList.size()]));
        };
    }
}
