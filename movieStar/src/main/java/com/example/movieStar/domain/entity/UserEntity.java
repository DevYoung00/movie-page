package com.example.movieStar.domain.entity;

import com.example.movieStar.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dto.UserDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name="movieUser")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userIdx;

    @Column(nullable = false,unique = true)
    private String email;

    private String name;

    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "userIdx")
    private List<MovieReviewEntity> reviewList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userIdx")
    private List<MovieStarEntity> starList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public UserEntity(String email ,String name, String password, UserRole role){
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }


    public UserDto toDto() {
        UserDto userDto = UserDto.builder()
                .password(password)
                .name(name)
                .email(email)
                .role(role)
                .build();
        return userDto;
    }
    /*
    public UserInfoDto toDto(){
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .password(password)
                .name(name)
                .nickName(nickName)
                .email(email)
                .phone(phone)
                .birth(birth)
                .profileImg(profileImg)
                .university(university)
                .userStatus(userStatus)
                .userScore(userScore)
                .role(role)
                .build();
        return userInfoDto;
    }*/




}