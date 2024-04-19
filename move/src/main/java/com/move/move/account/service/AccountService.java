package com.move.move.account.service;

import com.move.move.account.dto.SignResponse;

public interface AccountService {

    SignResponse signUp(String id, String password, String name, String role);

    SignResponse signIn(String id, String password) throws RuntimeException;
}
