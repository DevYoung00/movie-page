package com.example.movieStar.controller;

import com.example.movieStar.config.User.JwtTokenProvider;
import com.example.movieStar.domain.UserRole;
import com.example.movieStar.domain.entity.UserEntity;
import com.example.movieStar.service.UserService;
import dto.JoinUserDto;
import dto.LoginUserDto;
import dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @ResponseBody
    @PostMapping("/join")
    public UserEntity join(@RequestBody JoinUserDto joinUserDto) {
            UserEntity userEntity = userService.signup(joinUserDto);
            return userEntity;
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginUserDto loginUserDto) {
            UserDto userDto = userService.login(loginUserDto);
            String Email = userDto.getEmail();
            UserRole role = userDto.getRole();
            String result = jwtTokenProvider.createToken(Email, role);
            return  result;

    }
}
