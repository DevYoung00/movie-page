package com.example.movieStar.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="movieList")
@Getter
@Setter
@NoArgsConstructor
@Entity //테이블과 링크될 클래스
public class MovieListEntity {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieIdx;
    @Column
    private String title;
    @Column
    private String img;

    @Column(nullable = true)
    private int star;

    @Column(nullable = true)
    private String review;
    @Column(nullable = false)
    private int userId;

    @Builder
    public MovieListEntity(String title ,String img, int userId){
        this.title = title;
        this.img = img;
        this.userId = userId;
    }

    public void update(String title, int star, String review){
        this.title = title;
        this.star = star;
        this.review = review;

    }


}
