package com.move.move.service;

import com.move.move.config.security.JwtTokenProvider;
import com.move.move.dto.SignResponseDto;
import com.move.move.entity.User;
import com.move.move.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
        User user;
        if (role.equalsIgnoreCase("admin")) {
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .pwd(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        } else {
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .pwd(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        User savedUser = userRepository.save(user);
        SignResponseDto signResponseDto = new SignResponseDto();

        if (!savedUser.getName().isEmpty()) {
            signResponseDto.setSuccess(true);
        } else {
            signResponseDto.setSuccess(false);
        }
        return signResponseDto;
    }

    @Override
    public SignResponseDto signIn(String id, String password) throws RuntimeException {
        User user = userRepository.getByUid(id);

        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("비밀번호가 일치하지 않습니다.") {};
        }

        Cookie cookie = new Cookie("Authorization",
                jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles()));
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        cookie.setSecure(false);

        SignResponseDto signInResponseDto = SignResponseDto.builder()
                .success(true)
                .cookie(cookie)
                .build();

        return signInResponseDto;
    }
}
