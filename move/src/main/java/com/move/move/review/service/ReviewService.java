package com.move.move.review.service;

import com.move.move.review.dto.ReviewResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ReviewService {

    void writeReview(String movieCode, String content, HttpServletRequest request, Integer rating);

    List<ReviewResponse> getReview(String movieCode);
}
