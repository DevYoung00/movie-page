package com.example.movieStar.controller;


import com.example.movieStar.domain.entity.MovieListEntity;
import com.example.movieStar.domain.entity.MovieReviewEntity;
import com.example.movieStar.domain.entity.MovieStarEntity;
import com.example.movieStar.service.MovieService;
import dto.MovieInsertDto;
import dto.MovieResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/movies")
@RestController
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/search")
    public MovieResDto get(@RequestParam String query){
        return movieService.findBySearch(query);
    }

    @PostMapping("/insert")
    public MovieListEntity insertMovie(@RequestBody MovieInsertDto movieInsertDto){
        return movieService.insertMovie(movieInsertDto);
    }

    @PostMapping("/star")
    public MovieListEntity insertStar(@RequestParam String title, Integer star){
        return movieService.insertStar(title,star);
    }

    @PostMapping("/review")
    public MovieListEntity  insertReview(@RequestParam String title, String coment){
        return movieService.insertReview(title,coment);
    }

    @DeleteMapping("/delete")
    public String insertMovie(@RequestParam String title){
        movieService.deleteMovie(title);
        return "리스트에서 삭제되었습니다.";
    }

    @GetMapping("/get")
    public List<MovieListEntity> getMovie(){
        List<MovieListEntity> movieList =  movieService.getMovie();
        return movieList;
    }

}
