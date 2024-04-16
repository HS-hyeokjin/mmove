package com.move.move.review.service.impl;

import com.move.move.config.security.JwtTokenProvider;
import com.move.move.review.dto.ReviewResponse;
import com.move.move.movieinfo.entity.Movie;
import com.move.move.review.entity.Review;
import com.move.move.movieinfo.repository.MovieRepository;
import com.move.move.review.repository.ReviewRepository;
import com.move.move.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 영화 리뷰 댓글 서비스
 */
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

    /**
     * 리뷰를 작성하는 메서드
     * @param movieCode 영화 코드
     * @param content 리뷰 내용
     * @param request HTTP 요청 객체
     * @param rating 평점
     */
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

    /**
     * 특정 영화에 대한 리뷰를 가져오는 메서드
     * @param movieCode 영화 코드
     * @return 리뷰 목록
     */
    public List<ReviewResponse> getReview(String movieCode) {
        Movie movie = movieRepository.findByMovieCode(movieCode);
        List<Review> reviews = reviewRepository.findByMovie(movie);

        return reviews.stream()
                .map(review -> {
                    ReviewResponse reviewResponse = new ReviewResponse();
                    reviewResponse.setContent(review.getContent());
                    reviewResponse.setRating(review.getRating());
                    reviewResponse.setUserName(review.getUserName());
                    return reviewResponse;
                })
                .collect(Collectors.toList());
    }
}
