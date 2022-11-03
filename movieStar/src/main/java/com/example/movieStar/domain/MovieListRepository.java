package com.example.movieStar.domain;

import com.example.movieStar.domain.entity.MovieListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieListRepository extends JpaRepository<MovieListEntity,Long> {
    MovieListEntity findByTitle(String title);
    MovieListEntity findByTitleAndUserId(String  title, int userId);
    @Query("SELECT m FROM MovieListEntity m ORDER BY m.id DESC")
    List<MovieListEntity> findAllDesc();
    List<MovieListEntity> findAllByUserId(int userId);


    List<MovieListEntity> findAllByTitle(String title);



}
