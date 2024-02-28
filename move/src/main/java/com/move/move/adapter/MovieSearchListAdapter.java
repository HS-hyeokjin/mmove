package com.move.move.adapter;

import com.move.move.dto.MovieSearchListRequestDto;
import com.move.move.dto.MovieSearchListResponseDto;

import java.io.UnsupportedEncodingException;

public interface MovieSearchListAdapter {

    MovieSearchListResponseDto getMovieSearchList(MovieSearchListRequestDto movieSearchListRequestDto) throws UnsupportedEncodingException;
}
