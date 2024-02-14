package com.move.move.controller;

import com.move.move.adapter.MoviePosterAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TestController {

    private final MoviePosterAdapter moviePosterAdapter;

    public TestController(MoviePosterAdapter moviePosterAdapter) {
        this.moviePosterAdapter = moviePosterAdapter;
    }

    @GetMapping("/test")
    String test(Model model){

        String aa = moviePosterAdapter.searchMoviePoster("노량");
        model.addAttribute("posterUrl",aa);

        return "test";

    }
}
