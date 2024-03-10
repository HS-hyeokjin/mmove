package com.move.move.service;

import com.move.move.config.security.JwtTokenProvider;
import com.move.move.dto.SignInResponseDto;
import com.move.move.dto.SignUpResponseDto;
import com.move.move.entity.User;
import com.move.move.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AccountServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public SignUpResponseDto signUp(String id, String password, String name, String role) {
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
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();

        if (!savedUser.getName().isEmpty()) {
            setSuccessResult(signUpResponseDto);
        } else {
            setFailResult(signUpResponseDto);
        }
        return signUpResponseDto;
    }

    @Override
    public SignInResponseDto signIn(String id, String password) throws RuntimeException {
        User user = userRepository.getByUid(id);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }

        SignInResponseDto signInResponseDto = SignInResponseDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUid()),
                        user.getRoles()))
                .build();

        signInResponseDto.setSuccess(true);
        signInResponseDto.setCode(1);
        signInResponseDto.setSuccess(true);

        return signInResponseDto;
    }

    private void setSuccessResult(SignUpResponseDto signUpResponseDto) {
        signUpResponseDto.setSuccess(true);
        signUpResponseDto.setCode(1);
        signUpResponseDto.setMsg("성공");
    }

    private void setFailResult(SignUpResponseDto signUpResponseDto) {
        signUpResponseDto.setSuccess(false);
        signUpResponseDto.setCode(0);
        signUpResponseDto.setMsg("실패");
    }
}
