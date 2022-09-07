package com.example.movieStar.domain;

import com.example.movieStar.domain.entity.MovieStarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieStarRepository extends JpaRepository<MovieStarEntity,Long> {

}
