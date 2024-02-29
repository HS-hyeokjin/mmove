package com.move.move.service;

import com.move.move.dto.MovieSearchListResponseDto;

import java.io.UnsupportedEncodingException;

public interface MovieSearchListService {

    MovieSearchListResponseDto getMovieSearchList(String movieName, String directorName) throws UnsupportedEncodingException;
}
