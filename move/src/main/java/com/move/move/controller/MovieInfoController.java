package com.move.move.controller;

import com.move.move.dto.MovieInfoResponseDto;
import com.move.move.dto.ReviewResponseDto;
import com.move.move.service.MovieInfoService;
import com.move.move.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieInfoController {

    private final MovieInfoService movieInfoService;
    private final ReviewService reviewService;

    public MovieInfoController(MovieInfoService movieInfoService, ReviewService reviewService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
    }

    @GetMapping("/movie-info/{movieCode}")
    public String getMovieInfo(@PathVariable String movieCode, Model model) {
        MovieInfoResponseDto movieInfo = movieInfoService.getMovieInfo(movieCode);
        List<ReviewResponseDto> reviews = reviewService.getReview(movieCode);
        model.addAttribute("movieInfoData", movieInfo);
        model.addAttribute("reviews",reviews);
        return "movie/movie-info";
    }

    @PostMapping("/movie-info/{movieCode}/reviews")
    public String writeReview(@PathVariable String movieCode,
                              @RequestParam String content,
                              @RequestParam Integer rating,
                              HttpServletRequest request){
        reviewService.writeReview(movieCode,content,request,rating);
        return "redirect:/movie-info/" + movieCode;
    }
}
