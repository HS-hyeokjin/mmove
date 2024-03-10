package com.move.move.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponseDto {

    private boolean success;

    private int code;

    private String msg;

    private String token;

    @Builder
    public SignInResponseDto(boolean success, int code, String msg, String token){
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.token = token;
    }
}
