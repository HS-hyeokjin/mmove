package com.move.move.service;

import com.move.move.dto.MovieSearchListResponseDto;

public interface MovieSearchListService {

    MovieSearchListResponseDto getMovieSearchList(String movieName, String directorName);
}
