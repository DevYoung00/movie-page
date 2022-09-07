package com.example.movieStar.service;

import com.example.movieStar.config.User.JwtTokenProvider;
import com.example.movieStar.domain.UserRepository;
import com.example.movieStar.domain.entity.UserEntity;
import dto.JoinUserDto;
import dto.LoginUserDto;
import dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserEntity signup(JoinUserDto userDto) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            return userRepository.save(userDto.toEntity());
    }

    //로그인
    public UserDto login(LoginUserDto loginUserDto){
        UserEntity userEntity = userRepository.findByEmail(loginUserDto.getEmail());
        if (!passwordEncoder.matches(loginUserDto.getPassword(), userEntity.getPassword())) {
            //throw new Exception();
        }
        UserDto userDto = userEntity.toDto();
        return userDto;
    }

}
