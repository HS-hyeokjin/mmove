package com.move.move.service.impl;

import com.move.move.config.security.JwtTokenProvider;
import com.move.move.dto.ReviewResponseDto;
import com.move.move.entity.Movie;
import com.move.move.entity.Review;
import com.move.move.repository.MovieRepository;
import com.move.move.repository.ReviewRepository;
import com.move.move.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository, JwtTokenProvider jwtTokenProvider) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void writeReview(String movieCode, String content, HttpServletRequest request, Integer rating) {
        Movie movie = movieRepository.findByMovieCode(movieCode);

        if (movie == null) {
            movie = new Movie();
            movie.setMovieCode(movieCode);
            movieRepository.save(movie);
        }

        String token = jwtTokenProvider.resolveToken(request);
        String userName = jwtTokenProvider.getUsername(token);

        Review review = new Review();
        review.setMovie(movie);
        review.setUserName(userName);
        review.setContent(content);
        review.setRating(rating);

        reviewRepository.save(review);
    }

    public List<ReviewResponseDto> getReview(String movieCode) {
        Movie movie = movieRepository.findByMovieCode(movieCode);
        List<Review> reviews = reviewRepository.findByMovie(movie);

        return reviews.stream()
                .map(review -> {
                    ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
                    reviewResponseDto.setContent(review.getContent());
                    reviewResponseDto.setRating(review.getRating());
                    reviewResponseDto.setUserName(review.getUserName());
                    return reviewResponseDto;
                })
                .collect(Collectors.toList());
    }
}
