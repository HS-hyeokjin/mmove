package com.move.move.search.searchmovie.adapter;

import com.move.move.search.searchmovie.dto.MovieSearchListRequest;
import com.move.move.search.searchmovie.dto.MovieSearchListResponse;

import java.io.UnsupportedEncodingException;

public interface MovieSearchListAdapter {

    MovieSearchListResponse getMovieSearchList(MovieSearchListRequest movieSearchListRequest) throws UnsupportedEncodingException;
}
