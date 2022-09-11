package com.example.movieStar.controller;


import com.example.movieStar.config.BaseException;
import com.example.movieStar.domain.MovieListRepository;
import com.example.movieStar.domain.entity.MovieListEntity;
import com.example.movieStar.service.MovieService;
import dto.InsertStarReviewDto;
import dto.MovieInsertDto;
import dto.MovieResDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/movies")
@RestController
public class MovieController {

    private final MovieService movieService;
    private final MovieListRepository movieListRepository;

    @GetMapping("/search")
    public MovieResDto get(@RequestParam String query){
        return movieService.findBySearch(query);
    }

    @PostMapping("/insert")
    public MovieListEntity insertMovie(@RequestBody MovieInsertDto movieInsertDto) throws BaseException{
        return movieService.insertMovie(movieInsertDto);
    }

    @PostMapping("/starAndReview")
    public MovieListEntity insertStar(@RequestBody InsertStarReviewDto insertStarReviewDto)  {
        return movieService.insertStarAndReview(insertStarReviewDto);
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

    @GetMapping("/isExist")
    public Boolean isReviewAndStar(@RequestParam String title){
        MovieListEntity movieListEntity = movieListRepository.findByTitle(title);
        if(movieListEntity.getReview() != null || movieListEntity.getStar() >0 ){
            return true;
        }
        else return false;
    }


    @GetMapping("/getMovieEntity")
    public MovieListEntity getMovie(@RequestParam String title){
        MovieListEntity movieListEntity = movieListRepository.findByTitle(title);
        return movieListEntity;

    }


}
