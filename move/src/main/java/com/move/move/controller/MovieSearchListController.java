package com.move.move.controller;

import com.move.move.dto.MovieSearchListResponseDto;
import com.move.move.service.MovieSearchListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movie-search")
public class MovieSearchListController {

    private final MovieSearchListService movieSearchListService;

    public MovieSearchListController(MovieSearchListService movieSearchListService) {
        this.movieSearchListService = movieSearchListService;
    }


    @GetMapping
    public String getMovieSearch(){
        return "movie-search";
    }

    @GetMapping("/movie-list")
    public String getMovieList(Model model, @RequestParam(required = false) String movieName, @RequestParam(required = false) String directorName){
        MovieSearchListResponseDto movieSearchListResponseDto = movieSearchListService.getMovieSearchList(movieName, directorName);
        model.addAttribute("movieSearchListResponseDto",movieSearchListResponseDto);
        System.out.println(movieSearchListResponseDto.getMovieListResult().getTotCnt());
        return "movie-list";
    }
}
