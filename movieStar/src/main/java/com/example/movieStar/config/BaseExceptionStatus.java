package com.example.movieStar.config;

import lombok.Getter;

@Getter
public enum BaseExceptionStatus {

    FAILED_TO_INSERT(false,2000,"중복된 영화입니다.");

    private final boolean isFailed;
    private final int code;
    private final String message;

    private BaseExceptionStatus(boolean isFailed, int code, String message) {
        this.isFailed = isFailed;
        this.code = code;
        this.message = message;
    }
}
