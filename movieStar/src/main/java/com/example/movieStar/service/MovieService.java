package com.example.movieStar.service;

import com.example.movieStar.config.User.JwtTokenProvider;
import com.example.movieStar.domain.MovieListRepository;
import com.example.movieStar.domain.MovieReviewRepository;
import com.example.movieStar.domain.MovieStarRepository;
import com.example.movieStar.domain.UserRepository;
import com.example.movieStar.domain.entity.MovieListEntity;
import com.example.movieStar.domain.entity.MovieReviewEntity;
import com.example.movieStar.domain.entity.MovieStarEntity;
import com.example.movieStar.domain.entity.UserEntity;
import com.example.movieStar.naver.NaverClient;
import dto.MovieInsertDto;
import dto.MovieReqDto;
import dto.MovieResDto;
import dto.MovieStarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Properties;


@RequiredArgsConstructor
@Transactional
@Service
public class MovieService {
    private final NaverClient naverClient;
    private final MovieListRepository movieListRepository;
    private final MovieStarRepository movieStarRepository;
    private final MovieReviewRepository movieReviewRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public MovieResDto findBySearch(String search) {
        MovieResDto movieResDto =  naverClient.movieResDto(search);
        //var firstMovie = movieResDto.getItems().stream().findFirst().get();
        return movieResDto;
    }

    public MovieListEntity insertMovie( MovieInsertDto movieInsertDto){
        MovieListEntity movieList = new MovieListEntity(movieInsertDto.getTitle() , movieInsertDto.getImg());

        return movieListRepository.save(movieList);
    }

    public MovieListEntity insertStar(String title, int star){
        MovieListEntity movieListEntity = movieListRepository.findByTitle(title);
        movieListEntity.setStar(star);
        return movieListEntity;
    }

    public MovieListEntity insertReview(String title,String coment){
        MovieListEntity movieListEntity = movieListRepository.findByTitle(title);
        movieListEntity.setReview(coment);
        return movieListEntity;

    }

    public void deleteMovie(String title){
        MovieListEntity movieListEntity = movieListRepository.findByTitle(title);
        movieListRepository.delete(movieListEntity);
    }

    public List<MovieListEntity> getMovie() {
        return movieListRepository.findAllDesc();
    }

}