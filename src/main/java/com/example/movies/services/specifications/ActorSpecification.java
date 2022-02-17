package com.example.movies.services.specifications;

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

    public static Specification<Actor> actorFilter(ActorDetailsDTO filter){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> criteriaList = new ArrayList<>();

            if(StringUtils.hasText(filter.getName())){
                criteriaList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%"+filter.getName().toUpperCase(Locale.ROOT)+"%"));
            }
            if(filter.getGenderType() != null){
               criteriaList.add(criteriaBuilder.equal(root.get("gender"), filter.getGenderType()));
            }

            if(filter.getAge() != null){
                criteriaList.add(criteriaBuilder.lessThanOrEqualTo(root.get("age"), filter.getAge()));
            }

            return criteriaBuilder.and(criteriaList.toArray(new Predicate[criteriaList.size()]));
        };
    }
}
