package com.example.movieStar.domain;

import com.example.movieStar.domain.entity.MovieReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieReviewRepository extends JpaRepository<MovieReviewEntity,Long> {
}
