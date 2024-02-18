package com.move.move.service;

import com.move.move.adapter.MovieInfoAdapter;
import com.move.move.adapter.MoviePosterAdapter;
import com.move.move.dto.MovieInfoResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MovieInfoServiceImpl implements MovieInfoService{

    private final MovieInfoAdapter movieInfoAdapter;
    private final MoviePosterAdapter moviePosterAdapter;

    public MovieInfoServiceImpl(MovieInfoAdapter movieInfoAdapter, MoviePosterAdapter moviePosterAdapter) {
        this.movieInfoAdapter = movieInfoAdapter;
        this.moviePosterAdapter = moviePosterAdapter;
    }



    @Override
    public MovieInfoResponseDto getMovieInfo(String movieCode) {

        MovieInfoResponseDto movieInfoResponseDto = movieInfoAdapter.getMovieInfo(movieCode);
        movieInfoResponseDto.
                getMovieInfoResult().
                getMovieInfo().
                setImageUrl(moviePosterAdapter.searchMoviePoster(movieInfoResponseDto.getMovieInfoResult().getMovieInfo().getMovieNm()));
        return movieInfoResponseDto;
    }
}
