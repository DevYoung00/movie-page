package com.example.movieStar.service;

import com.example.movieStar.config.BaseException;
import com.example.movieStar.config.User.JwtTokenProvider;
import com.example.movieStar.domain.UserRepository;
import com.example.movieStar.domain.entity.MovieListEntity;
import com.example.movieStar.domain.entity.UserEntity;
import dto.JoinUserDto;
import dto.LoginUserDto;
import dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static com.example.movieStar.config.BaseResponseStatus.*;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserEntity signup(JoinUserDto userDto) throws BaseException {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new BaseException(EXIST_USER_EMAIL);
            //이메일 중복 validation
        }
        return userRepository.save(userDto.toEntity());
    }

    //로그인
    public UserDto login(LoginUserDto loginUserDto) throws BaseException{
        UserEntity userEntity = userRepository.findByEmail(loginUserDto.getEmail());
        if(userEntity == null) {
            throw new BaseException(NO_USER_ERROR);
        }
        if (!passwordEncoder.matches(loginUserDto.getPassword(), userEntity.getPassword())) {
            throw new BaseException(FAIL_TO_LOGIN);
        }
        UserDto userDto = userEntity.toDto();
        return userDto;
    }

    public int isExist(String email){
       if((userRepository.findByEmail(email)) != null){
           return 1;
       }
       else return 0;
    }

    //토근 유효성 검사
    public Boolean UserAuth(String token){
        if ((jwtTokenProvider.getUserPk(token)) != null){
            return true;
        }
        else {
            return false;
        }   }

    public int GetUser(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String userEmail = jwtTokenProvider.getUserPk(token);
       UserEntity userEntity = userRepository.findByEmail(userEmail);
        int userId = userEntity.getUserIdx();
        return userId;
    }
    public String GetName(int userId){
        UserEntity userEntity = userRepository.findByUserIdx(userId);
        return userEntity.getName();
    }



}
