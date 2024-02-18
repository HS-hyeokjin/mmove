package com.move.move.controller;

import com.move.move.dto.MovieInfoResponseDto;
import com.move.move.service.MovieInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MovieInfoController {
    private final MovieInfoService movieInfoService;

    public MovieInfoController(MovieInfoService movieInfoService) {
        this.movieInfoService = movieInfoService;
    }

    @GetMapping("/movie-info/{movieCode}")
    public String getMovieInfo(@PathVariable String movieCode, Model model) {
        MovieInfoResponseDto movieInfo = movieInfoService.getMovieInfo(movieCode);
        model.addAttribute("movieInfoData", movieInfo);

        return "movie-info";
    }
}
