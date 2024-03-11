package com.move.move.controller;

import com.move.move.dto.SignResponseDto;
import com.move.move.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/sign-up")
    String signUp() {
        return "sign-up";
    }


    @PostMapping("/sign-up")
    public String signUp(@RequestParam String id, @RequestParam String password, @RequestParam String name) {
        SignResponseDto signResponseDto = accountService.signUp(id, password, name, "user");
        if (signResponseDto.isSuccess()) {
            return "redirect:/account/sign-in";
        } else {
            return "redirect:/account/sign-in?error";
        }
    }

    @GetMapping("/sign-in")
    String singIn() {
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestParam String id, @RequestParam String password) {
        SignResponseDto signResponseDto = accountService.signIn(id, password);
        if (signResponseDto.isSuccess()) {
            return "redirect:/daily-box-office";
        } else {
            return "account/sign-in";
        }
    }
}
