package com.move.move.adapter;

import com.move.move.dto.MovieDetailResponseDto;

public interface MovieDetailAdapter {

    MovieDetailResponseDto searchMovieDetail(String movieTitle);

    String searchMoviePoster(String movieTitle);

}
