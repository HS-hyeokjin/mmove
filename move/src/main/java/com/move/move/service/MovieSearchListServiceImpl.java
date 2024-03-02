package com.move.move.service;

import com.move.move.adapter.MovieDetailAdapter;
import com.move.move.adapter.MovieSearchListAdapter;
import com.move.move.dto.MovieSearchListRequestDto;
import com.move.move.dto.MovieSearchListResponseDto;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class MovieSearchListServiceImpl implements MovieSearchListService {

    private final MovieSearchListAdapter movieSearchListAdapter;
    private final MovieDetailAdapter movieDetailAdapter;

    public MovieSearchListServiceImpl(MovieSearchListAdapter movieSearchListAdapter, MovieDetailAdapter movieDetailAdapter) {
        this.movieSearchListAdapter = movieSearchListAdapter;
        this.movieDetailAdapter = movieDetailAdapter;
    }

    @Override
    public MovieSearchListResponseDto getMovieSearchList(String movieName, String directorName) throws UnsupportedEncodingException {
        MovieSearchListRequestDto movieSearchListRequestDto = new MovieSearchListRequestDto();
        movieSearchListRequestDto.setMovieNm(movieName);
        movieSearchListRequestDto.setDirectorNm(directorName);

        MovieSearchListResponseDto movieSearchListResponseDto = movieSearchListAdapter.getMovieSearchList(movieSearchListRequestDto);

        List<MovieSearchListResponseDto.MovieDTO> movieDTOList = movieSearchListResponseDto.getMovieListResult().getMovieList();
        for (MovieSearchListResponseDto.MovieDTO movieDTO : movieDTOList) {
            String movieTitle = movieDTO.getMovieNm();

            String posterUrl = movieDetailAdapter.searchMoviePoster(movieTitle);
            movieDTO.setPosterUrl(posterUrl);
        }
        return movieSearchListResponseDto;
    }
}
