package com.move.move.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignResponseDto {

    private boolean success;

    private String msg;

    private String token;

    @Builder
    public SignResponseDto(boolean success, String msg, String token){
        this.success = success;
        this.msg = msg;
        this.token = token;
    }
}
