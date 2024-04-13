package com.move.move.account.contoller;

import com.move.move.account.dto.SignResponseDto;
import com.move.move.account.service.AccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/sign-up")
    String signUp() {
        return "/account/sign-up";
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
        return "account/sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestParam String id, @RequestParam String password, HttpServletResponse response) {
        SignResponseDto signResponseDto = accountService.signIn(id, password);
        if (signResponseDto.isSuccess()) {
            response.addCookie(signResponseDto.getCookie());
            return "redirect:/daily-box-office";
        } else {
            return "account/sign-in";
        }
    }

    @GetMapping("/logout")
    public String logout(@CookieValue(value = "Authorization", defaultValue = "", required = false) Cookie cookie,
                         HttpServletResponse response) {
        cookie.setValue(null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "account/sign-in";
    }
}
