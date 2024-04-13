package com.move.move.searchmovie.service;

import com.move.move.searchmovie.dto.MovieSearchListResponseDto;

import java.io.UnsupportedEncodingException;

public interface MovieSearchListService {

    MovieSearchListResponseDto getMovieSearchList(String movieName, String directorName, String curPage) throws UnsupportedEncodingException;
}
