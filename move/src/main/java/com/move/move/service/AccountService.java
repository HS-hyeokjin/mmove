package com.move.move.service;

import com.move.move.dto.SignResponseDto;

public interface AccountService {

    SignResponseDto signUp(String id, String password, String name, String role);

    SignResponseDto signIn(String id, String password) throws RuntimeException;
}
