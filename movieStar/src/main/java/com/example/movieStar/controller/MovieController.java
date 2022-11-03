package com.example.movieStar.controller;


import com.example.movieStar.config.BaseException;
import com.example.movieStar.domain.MovieListRepository;
import com.example.movieStar.domain.entity.MovieListEntity;
import com.example.movieStar.service.MovieService;
import com.example.movieStar.service.UserService;
import dto.InsertStarReviewDto;
import dto.MovieInsertDto;
import dto.MovieResDto;
import dto.MovieReviewDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/movies")
@RestController
public class MovieController {

    private final MovieService movieService;
    private final UserService userService;
    private final MovieListRepository movieListRepository;

    @GetMapping("/search")
    public MovieResDto get(@RequestParam String query){
        return movieService.findBySearch(query);
    }

    @PostMapping("/insert")
    public MovieListEntity insertMovie(HttpServletRequest request, @RequestBody MovieInsertDto movieInsertDto) throws BaseException{
        return movieService.insertMovie(request, movieInsertDto);
    }

    @PostMapping("/starAndReview")
    public MovieListEntity insertStar(HttpServletRequest request,@RequestBody InsertStarReviewDto insertStarReviewDto)  {
        return movieService.insertStarAndReview(request,insertStarReviewDto);
    }

    @DeleteMapping("/delete")
    public String insertMovie(HttpServletRequest request, @RequestParam String title){
        movieService.deleteMovie(request,title);
        return "리스트에서 삭제되었습니다.";
    }

    @GetMapping("/get")
    public List<MovieListEntity> getMovie(HttpServletRequest request){
        List<MovieListEntity> movieList =  movieService.getMovie(request);
        return movieList;
    }

    @GetMapping("/isExist")
    public Boolean isReviewAndStar(HttpServletRequest request,@RequestParam String title){
        int userId = userService.GetUser(request);
        MovieListEntity movieListEntity = movieListRepository.findByTitleAndUserId(title , userId);
        if(movieListEntity.getReview() != null || movieListEntity.getStar() >0 ){
            return true;
        }
        else return false;
    }


    @GetMapping("/getMovieEntity")
    public MovieListEntity getMovie(HttpServletRequest request,@RequestParam String title){
        int userId = userService.GetUser(request);
        MovieListEntity movieListEntity = movieListRepository.findByTitleAndUserId(title , userId);
        return movieListEntity;
    }
    @GetMapping("/getReview")
     public List<MovieReviewDto> getReview(@RequestParam String title){
        List<MovieReviewDto> movieReviewList = movieService.getReview(title);
        return movieReviewList;

    }
    @PatchMapping("/updateReview")
    public MovieListEntity updateReview(HttpServletRequest request,@RequestBody InsertStarReviewDto insertStarReviewDto){
        return movieService.updateReview(request,insertStarReviewDto);

    }



}
