package com.move.move.service;

import com.move.move.adapter.MovieSearchListAdapter;
import com.move.move.dto.MovieSearchListRequestDto;
import com.move.move.dto.MovieSearchListResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MovieSearchListServiceImpl implements MovieSearchListService {

    private final MovieSearchListAdapter movieSearchListAdapter;

    public MovieSearchListServiceImpl(MovieSearchListAdapter movieSearchListAdapter) {
        this.movieSearchListAdapter = movieSearchListAdapter;
    }

    @Override
    public MovieSearchListResponseDto getMovieSearchList(String movieName, String directorName) {
        MovieSearchListRequestDto movieSearchListRequestDto = new MovieSearchListRequestDto();
        movieSearchListRequestDto.setMovieNm(movieName);
        movieSearchListRequestDto.setDirectorNm(directorName);
        return movieSearchListAdapter.getMovieSearchList(movieSearchListRequestDto);
    }
}
