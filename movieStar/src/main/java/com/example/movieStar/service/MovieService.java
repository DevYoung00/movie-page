package com.example.movieStar.service;
import static com.example.movieStar.config.BaseExceptionStatus.*;
import com.example.movieStar.config.BaseException;
import com.example.movieStar.config.User.JwtTokenProvider;
import com.example.movieStar.domain.MovieListRepository;
import com.example.movieStar.domain.UserRepository;
import com.example.movieStar.domain.entity.MovieListEntity;
import com.example.movieStar.naver.NaverClient;
import dto.InsertStarReviewDto;
import dto.MovieInsertDto;
import dto.MovieResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class MovieService {

    private final NaverClient naverClient;
    private final MovieListRepository movieListRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public MovieResDto findBySearch(String search) {
        MovieResDto movieResDto =  naverClient.movieResDto(search);
        //var firstMovie = movieResDto.getItems().stream().findFirst().get();
        return movieResDto;
    }

    public MovieListEntity insertMovie( MovieInsertDto movieInsertDto) throws BaseException{
        MovieListEntity movieList = new MovieListEntity(movieInsertDto.getTitle() , movieInsertDto.getImg());
        if( movieListRepository.findByTitle(movieInsertDto.getTitle() ) != null){
            throw new BaseException(FAILED_TO_INSERT);
        }
        return movieListRepository.save(movieList);
    }

    public MovieListEntity insertStarAndReview(InsertStarReviewDto insertStarReviewDto)   {
        MovieListEntity movieListEntity = movieListRepository.findByTitle(insertStarReviewDto.getTitle());
        movieListEntity.setStar(insertStarReviewDto.getStar());
        movieListEntity.setReview(insertStarReviewDto.getComent());
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