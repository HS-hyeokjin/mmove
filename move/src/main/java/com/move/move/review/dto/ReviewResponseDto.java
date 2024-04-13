package com.move.move.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {

    private String userName;

    private String content;

    private int rating;
}
