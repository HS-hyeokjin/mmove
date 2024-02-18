package com.move.move.service;

import com.move.move.dto.MovieInfoResponseDto;

public interface MovieInfoService {

    MovieInfoResponseDto getMovieInfo(String movieCode);
}
