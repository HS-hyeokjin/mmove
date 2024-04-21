package com.move.move.account.contoller;

import com.move.move.account.dto.SignResponse;
import com.move.move.account.service.AccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class SignInController {

    private final AccountService accountService;

    public SignInController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/sign-in")
    String singIn() {
        return "account/sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestParam String id, @RequestParam String password, HttpServletResponse response) {
        SignResponse signResponse = accountService.signIn(id, password);
        if (signResponse.isSuccess()) {
            response.addCookie(signResponse.getCookie());
            return "redirect:/daily-box-office";
        } else {
            return "account/sign-in";
        }
    }

    @GetMapping("/sign-out")
    public String signOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/account/sign-in";
    }
}

