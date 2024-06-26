package com.move.move.account.dto;

import jakarta.servlet.http.Cookie;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignResponse {

    private boolean success;

    private String msg;

    private Cookie cookie;

    @Builder
    public SignResponse(boolean success, Cookie cookie , String msg){
        this.success = success;
        this.cookie = cookie;
        this.msg = msg;
    }
}
