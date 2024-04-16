package com.move.move.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponse {

    private String userName;

    private String content;

    private int rating;
}
