package com.move.move.service.impl;

import com.move.move.adapter.MovieDetailAdapter;
import com.move.move.adapter.MovieInfoAdapter;
import com.move.move.adapter.PersonDetailAdapter;
import com.move.move.dto.MovieDetailResponseDto;
import com.move.move.dto.MovieInfoResponseDto;
import com.move.move.repository.MovieRepository;
import com.move.move.repository.ReviewRepository;
import com.move.move.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {

    @Value("${tmdb.image.url}")
    String imageUrl;

    @Value("${tmdb.image.url.w780}")
    String imageUrl2;

    private final MovieInfoAdapter movieInfoAdapter;
    private final MovieDetailAdapter movieDetailAdapter;
    private final PersonDetailAdapter personDetailAdapter;

    public MovieInfoServiceImpl(MovieInfoAdapter movieInfoAdapter, MovieDetailAdapter movieDetailAdapter, PersonDetailAdapter personDetailAdapter) {
        this.movieInfoAdapter = movieInfoAdapter;
        this.movieDetailAdapter = movieDetailAdapter;
        this.personDetailAdapter = personDetailAdapter;
    }


    @Override
    public MovieInfoResponseDto getMovieInfo(String movieCode) {

        MovieInfoResponseDto movieInfoResponseDto = movieInfoAdapter.getMovieInfo(movieCode);
        MovieDetailResponseDto movieDetailResponseDto = movieDetailAdapter.searchMovieDetail(movieInfoResponseDto.getMovieInfoResult().getMovieInfo().getMovieNm());

        List<MovieInfoResponseDto.Actor> actors = movieInfoResponseDto.getMovieInfoResult().getMovieInfo().getActors();
        for (MovieInfoResponseDto.Actor actor : actors) {
            String actorImageUrl;

            if ("한국".equals(movieInfoResponseDto.getMovieInfoResult().getMovieInfo().getNations().get(0).getNationNm())) {
                actorImageUrl = personDetailAdapter.personImageUrl(actor.getPeopleNm());
            } else {
                actorImageUrl = personDetailAdapter.personImageUrl(actor.getPeopleNmEn());
            }
            actor.setPeopleImageUrl(actorImageUrl);
        }

        movieInfoResponseDto.getMovieInfoResult().getMovieInfo().
                setPosterImageUrl(imageUrl + movieDetailResponseDto.getMovieDetailResultDto().getPosterPath());
        movieInfoResponseDto.getMovieInfoResult().getMovieInfo().
                setBackDropImageUrl(imageUrl2 + movieDetailResponseDto.getMovieDetailResultDto().getBackdropPath());
        movieInfoResponseDto.getMovieInfoResult().getMovieInfo().
                setOverview(movieDetailResponseDto.getMovieDetailResultDto().getOverview());
        movieInfoResponseDto.getMovieInfoResult().getMovieInfo().
                setPopularity(movieDetailResponseDto.getMovieDetailResultDto().getPopularity());

        return movieInfoResponseDto;
    }
}