package com.move.move.account.service.impl;

import com.move.move.config.security.JwtTokenProvider;
import com.move.move.account.dto.SignResponseDto;
import com.move.move.account.entity.User;
import com.move.move.exception.DuplicateIdException;
import com.move.move.account.repository.UserRepository;
import com.move.move.account.service.AccountService;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public SignResponseDto signUp(String id, String password, String name, String role) {
        validateUniqueUserId(id);

        User user = createUser(id, password, name, role);
        User savedUser = userRepository.save(user);

        return buildSignUpResponse(savedUser);
    }

    private void validateUniqueUserId(String id) {
        if (userRepository.existsByUid(id)) {
            throw new DuplicateIdException("이미 가입된 아이디입니다.");
        }
    }

    private User createUser(String id, String password, String name, String role) {
        List<String> roles = Collections.singletonList(role.equalsIgnoreCase("admin") ? "ROLE_ADMIN" : "ROLE_USER");

        return User.builder()
                .uid(id)
                .name(name)
                .pwd(passwordEncoder.encode(password))
                .roles(roles)
                .build();
    }

    private SignResponseDto buildSignUpResponse(User savedUser) {
        SignResponseDto signResponseDto = new SignResponseDto();
        signResponseDto.setSuccess(!savedUser.getName().isEmpty());
        return signResponseDto;
    }

    @Override
    public SignResponseDto signIn(String id, String password) throws RuntimeException {
        User user = findUserById(id);
        validateUserPassword(user, password);
        Cookie cookie = createAuthorizationCookie(user);

        return buildSignInResponse(cookie);
    }

    private User findUserById(String id) {
        User user = userRepository.getByUid(id);
        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        return user;
    }

    private void validateUserPassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("비밀번호가 일치하지 않습니다.") {
            };
        }
    }

    private Cookie createAuthorizationCookie(User user) {
        Cookie cookie = new Cookie("Authorization",
                jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles()));
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        cookie.setSecure(false);
        return cookie;
    }

    private SignResponseDto buildSignInResponse(Cookie cookie) {
        return SignResponseDto.builder()
                .success(true)
                .cookie(cookie)
                .build();
    }
}
