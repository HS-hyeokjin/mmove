package com.move.move.service;

import com.move.move.dto.ReviewResponseDto;
import com.move.move.entity.Review;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ReviewService {

    void writeReview(String movieCode, String content, HttpServletRequest request, Integer rating);

    List<ReviewResponseDto> getReview(String movieCode);
}
