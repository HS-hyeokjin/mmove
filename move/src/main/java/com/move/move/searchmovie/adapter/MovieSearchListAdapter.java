package com.move.move.searchmovie.adapter;

import com.move.move.searchmovie.dto.MovieSearchListRequestDto;
import com.move.move.searchmovie.dto.MovieSearchListResponseDto;

import java.io.UnsupportedEncodingException;

public interface MovieSearchListAdapter {

    MovieSearchListResponseDto getMovieSearchList(MovieSearchListRequestDto movieSearchListRequestDto) throws UnsupportedEncodingException;
}
