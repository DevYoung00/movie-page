package com.example.movieStar.naver;

import dto.MovieResDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NaverClient {

    @Value("${naver_client_id}")
    private String naverClientId;

    @Value("${naver_client_secret}")
    private String naverSecret;

    @Value("${naver_url_search_naver}")
    private String naverMovieSearchUrl;


    public MovieResDto movieResDto(String search) {

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<MovieResDto>(){};

        var responseEntity = new RestTemplate()
                .exchange(
                        naverMovieSearchUrl,
                        HttpMethod.GET,
                        httpEntity,
                        responseType,
                        search
                );

        return responseEntity.getBody();

    }

}