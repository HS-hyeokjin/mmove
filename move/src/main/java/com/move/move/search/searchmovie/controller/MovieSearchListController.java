package com.move.move.search.searchmovie.controller;

import com.move.move.search.searchmovie.dto.MovieSearchListResponse;
import com.move.move.search.searchmovie.service.MovieSearchListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/movie-search")
public class MovieSearchListController {

    private final MovieSearchListService movieSearchListService;

    public MovieSearchListController(MovieSearchListService movieSearchListService) {
        this.movieSearchListService = movieSearchListService;
    }

    @GetMapping
    public String getMovieSearch() {
        return "movie/movie-search";
    }

    @GetMapping("/movie-list")
    public String getMovieList(Model model,
                               @RequestParam(required = false) String movieName,
                               @RequestParam(required = false) String directorName,
                               @RequestParam(required = false) String page) throws UnsupportedEncodingException {
        MovieSearchListResponse movieSearchListResponse = movieSearchListService.getMovieSearchList(movieName, directorName, page);
        model.addAttribute("movieSearchListResponseDto", movieSearchListResponse);
        model.addAttribute("searchMovieName", movieName);
        model.addAttribute("searchDirectorName", directorName);

        int totalCnt = Integer.parseInt(movieSearchListResponse.getMovieListResult().getTotCnt());
        int totalPages = (int) Math.ceil((double) totalCnt / 10);
        model.addAttribute("totalPages", totalPages);
        return "movie/movie-list";
    }
}
