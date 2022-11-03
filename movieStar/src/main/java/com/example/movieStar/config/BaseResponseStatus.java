package com.example.movieStar.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    //성공
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    //실패
    FAILED_TO_INSERT(false,2000,"중복된 영화입니다."),
    EXIST_USER_EMAIL(false,2001,"중복된 이메일입니다."),
    NO_USER_ERROR(false,2002, "이메일에 해당하는 유저가 없습니다."),
    FAIL_TO_LOGIN(false, 2003, "비밀번호가 틀렸습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
