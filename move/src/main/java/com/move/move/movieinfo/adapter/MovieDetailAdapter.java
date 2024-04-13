package com.move.move.movieinfo.adapter;

import com.move.move.movieinfo.dto.MovieDetailResponseDto;

public interface MovieDetailAdapter {

    MovieDetailResponseDto searchMovieDetail(String movieTitle);

    String searchMoviePoster(String movieTitle);

}
