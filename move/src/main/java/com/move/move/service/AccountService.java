package com.move.move.service;

import com.move.move.dto.SignInResponseDto;
import com.move.move.dto.SignUpResponseDto;

public interface AccountService {

    SignUpResponseDto signUp(String id, String password, String name, String role);

    SignInResponseDto signIn(String id, String password) throws RuntimeException;
}
