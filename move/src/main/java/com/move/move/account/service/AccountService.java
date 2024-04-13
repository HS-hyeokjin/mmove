package com.move.move.account.service;

import com.move.move.account.dto.SignResponseDto;

public interface AccountService {

    SignResponseDto signUp(String id, String password, String name, String role);

    SignResponseDto signIn(String id, String password) throws RuntimeException;
}
