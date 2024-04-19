package com.move.move.account.contoller;

import com.move.move.account.dto.SignResponse;
import com.move.move.account.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class SignUpController {

    private final AccountService accountService;

    public SignUpController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/sign-up")
    String signUp() {
        return "/accont/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestParam String id, @RequestParam String password, @RequestParam String name) {
        SignResponse signResponse = accountService.signUp(id, password, name, "user");
        if (signResponse.isSuccess()) {
            return "redirect:/account/sign-in";
        } else {
            return "redirect:/account/sign-in?error";
        }
    }
}
