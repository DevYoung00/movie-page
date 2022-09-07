package com.example.movieStar.domain.entity;

import lombok.*;

import javax.persistence.*;

@Table(name="movieStar")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class MovieStarEntity {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long starIdx;

    @Column
    private Integer star;

    @Column
    private String title;
    @Column
    private String searchTitle;

    @Column
    private String subtitle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity userIdx;

    @Builder
    public MovieStarEntity(String title,int star, String subtitle,String searchTitle){
        this.title = title;
        this.star = star;
        this.searchTitle =searchTitle;
        this.subtitle =subtitle;
    }


}
