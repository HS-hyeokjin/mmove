package com.move.move.search.searchmovie.service;

import com.move.move.search.searchmovie.dto.MovieSearchListResponse;

import java.io.UnsupportedEncodingException;

public interface MovieSearchListService {

    MovieSearchListResponse getMovieSearchList(String movieName, String directorName, String curPage) throws UnsupportedEncodingException;
}
