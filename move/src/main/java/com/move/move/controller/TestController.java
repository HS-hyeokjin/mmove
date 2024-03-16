package com.move.move.controller;

import com.move.move.adapter.MoviePosterAdapter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TestController {

    @GetMapping("/test")
    String test(HttpServletRequest request, @CookieValue(value = "Authorization", defaultValue = "", required = false) String test){


        return "test";

    }
}
