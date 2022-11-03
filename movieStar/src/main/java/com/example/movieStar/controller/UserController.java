package com.example.movieStar.controller;

import com.example.movieStar.config.BaseException;
import com.example.movieStar.config.BaseResponse;
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
    public  UserEntity join(@RequestBody JoinUserDto joinUserDto) throws BaseException {
            UserEntity userEntity = userService.signup(joinUserDto);
            return userEntity;
    }

    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody LoginUserDto loginUserDto) throws BaseException {
        try{
            UserDto userDto = userService.login(loginUserDto);
            String Email = userDto.getEmail();
            UserRole role = userDto.getRole();
            String result = jwtTokenProvider.createToken(Email, role);
            return  new BaseResponse<>(result);
        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }


    }
    @ResponseBody
    @GetMapping("/isExist")
    public int isExist(@RequestParam String email){
        int result = userService.isExist(email);
        return result;
    }
    @ResponseBody
    @GetMapping("/isAuth")
    public boolean isAuth(@RequestParam String token) {
        boolean result = userService.UserAuth(token);
        return result;
    }


}
