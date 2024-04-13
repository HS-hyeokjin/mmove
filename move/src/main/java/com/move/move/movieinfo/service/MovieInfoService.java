package com.move.move.movieinfo.service;

import com.move.move.movieinfo.dto.MovieInfoResponseDto;

public interface MovieInfoService {

    MovieInfoResponseDto getMovieInfo(String movieCode);
}
