package com.move.move.controller;

import com.move.move.service.MovieInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieInfoController {
    private final MovieInfoService movieInfoService;


    public MovieInfoController(MovieInfoService movieInfoService) {
        this.movieInfoService = movieInfoService;
    }

    @GetMapping("/movie-info")
    public String getMovieInfo(@RequestParam String movieCode){
        
    }

}
