package com.move.move.controller;

import com.move.move.adapter.MovieImageAdapter;
import jakarta.persistence.metamodel.SetAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private final MovieImageAdapter movieImageAdapter;

    public TestController(MovieImageAdapter movieImageAdapter) {
        this.movieImageAdapter = movieImageAdapter;
    }

    @GetMapping("/test")
    String test(Model model){

        String aa = movieImageAdapter.searchMoviePoster("dwar");
        model.addAttribute("posterUrl",aa);

        return "test";

    }
}
