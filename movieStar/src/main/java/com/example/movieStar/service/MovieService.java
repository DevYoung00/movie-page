package com.example.movieStar.service;
import static com.example.movieStar.config.BaseResponseStatus.*;
import com.example.movieStar.config.BaseException;
import com.example.movieStar.config.User.JwtTokenProvider;
import com.example.movieStar.domain.MovieListRepository;
import com.example.movieStar.domain.UserRepository;
import com.example.movieStar.domain.entity.MovieListEntity;
import com.example.movieStar.naver.NaverClient;
import dto.InsertStarReviewDto;
import dto.MovieInsertDto;
import dto.MovieResDto;
import dto.MovieReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional
@Service
public class MovieService {

    private final NaverClient naverClient;
    private final MovieListRepository movieListRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserService userService;

    public MovieResDto findBySearch(String search) {
        MovieResDto movieResDto =  naverClient.movieResDto(search);
        //var firstMovie = movieResDto.getItems().stream().findFirst().get();
        return movieResDto;
    }

    public MovieListEntity insertMovie(HttpServletRequest request, MovieInsertDto movieInsertDto) throws BaseException{
        int userId = userService.GetUser(request);
        MovieListEntity movieList = new MovieListEntity(movieInsertDto.getTitle() , movieInsertDto.getImg(), userId);
        if( movieListRepository.findByTitleAndUserId(movieInsertDto.getTitle() , userId) != null){
            throw new BaseException(FAILED_TO_INSERT);
        }
        return movieListRepository.save(movieList);
    }

    public MovieListEntity insertStarAndReview(HttpServletRequest request, InsertStarReviewDto insertStarReviewDto)   {
        int userId = userService.GetUser(request);
        MovieListEntity movieListEntity = movieListRepository.findByTitleAndUserId(insertStarReviewDto.getTitle() , userId);
        movieListEntity.setStar(insertStarReviewDto.getStar());
        movieListEntity.setReview(insertStarReviewDto.getComent());
        return movieListEntity;
    }


    public void deleteMovie(HttpServletRequest request,String title){
        int userId = userService.GetUser(request);
        MovieListEntity movieListEntity = movieListRepository.findByTitleAndUserId(title , userId);
        movieListRepository.delete(movieListEntity);
    }

    public List<MovieListEntity> getMovie(HttpServletRequest request) {
        int userId = userService.GetUser(request);
        return movieListRepository.findAllByUserId(userId);
    }
    public List<MovieReviewDto> getReview(String title){
        List<MovieListEntity> movieListEntity = movieListRepository.findAllByTitle(title);
        List<MovieReviewDto> movieReviewDto = movieListEntity.stream()
                .map(m -> new MovieReviewDto(m.getTitle(),m.getReview(),m.getStar(),  userService.GetName(m.getUserId())))
                .collect(Collectors.toList());
        return movieReviewDto;
    }
    public MovieListEntity updateReview(HttpServletRequest request,InsertStarReviewDto insertStarReviewDto){
        int userId = userService.GetUser(request);
        MovieListEntity movieListEntity = movieListRepository.findByTitleAndUserId(insertStarReviewDto.getTitle() , userId);
        movieListEntity.update(insertStarReviewDto.getTitle(), insertStarReviewDto.getStar(), insertStarReviewDto.getComent());
        return movieListEntity;
    }


}