package com.example.movieStar.domain;

import com.example.movieStar.domain.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);
    UserEntity findByUserIdx(int userId);
}
