package com.example.movieStar.domain.entity;

import lombok.*;

import javax.persistence.*;

@Table(name="movieReview")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class MovieReviewEntity {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReviewIdx;

    @Column
    private String reviewContent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity userIdx;


    @Builder
    public MovieReviewEntity(String reviewContent){
      this.reviewContent = reviewContent;
    }


}
