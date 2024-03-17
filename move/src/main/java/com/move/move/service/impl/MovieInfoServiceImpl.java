package com.move.move.service.impl;

import com.move.move.adapter.MovieDetailAdapter;
import com.move.move.adapter.MovieInfoAdapter;
import com.move.move.adapter.PersonDetailAdapter;
import com.move.move.dto.MovieDetailResponseDto;
import com.move.move.dto.MovieInfoResponseDto;
import com.move.move.exception.ApiRequestException;
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
        MovieDetailResponseDto movieDetailResponseDto = searchMovieDetail(movieInfoResponseDto);
        if (movieDetailResponseDto == null) {
            throw new ApiRequestException("영화 상세 정보를 가져올 수 없습니다.");
        }
        setActorImages(movieInfoResponseDto, movieDetailResponseDto);
        setAdditionalMovieInfo(movieInfoResponseDto, movieDetailResponseDto);
        return movieInfoResponseDto;
    }

    private MovieDetailResponseDto searchMovieDetail(MovieInfoResponseDto movieInfoResponseDto) {
        String movieName = movieInfoResponseDto.getMovieInfoResult().getMovieInfo().getMovieNm();
        return movieDetailAdapter.searchMovieDetail(movieName);
    }

    private void setActorImages(MovieInfoResponseDto movieInfoResponseDto, MovieDetailResponseDto movieDetailResponseDto) {
        String nationName = movieInfoResponseDto.getMovieInfoResult().getMovieInfo().getNations().get(0).getNationNm();
        List<MovieInfoResponseDto.Actor> actors = movieInfoResponseDto.getMovieInfoResult().getMovieInfo().getActors();
        for (MovieInfoResponseDto.Actor actor : actors) {
            String actorName = "한국".equals(nationName) ? actor.getPeopleNm() : actor.getPeopleNmEn();
            actor.setPeopleImageUrl(personDetailAdapter.personImageUrl(actorName));
        }
    }

    private void setAdditionalMovieInfo(MovieInfoResponseDto movieInfoResponseDto, MovieDetailResponseDto movieDetailResponseDto) {
        String posterPath = movieDetailResponseDto.getMovieDetailResultDto().getPosterPath();
        String backdropPath = movieDetailResponseDto.getMovieDetailResultDto().getBackdropPath();
        String overview = movieDetailResponseDto.getMovieDetailResultDto().getOverview();
        double popularity = movieDetailResponseDto.getMovieDetailResultDto().getPopularity();

        movieInfoResponseDto.getMovieInfoResult().getMovieInfo().setPosterImageUrl(imageUrl + posterPath);
        movieInfoResponseDto.getMovieInfoResult().getMovieInfo().setBackDropImageUrl(imageUrl2 + backdropPath);
        movieInfoResponseDto.getMovieInfoResult().getMovieInfo().setOverview(overview);
        movieInfoResponseDto.getMovieInfoResult().getMovieInfo().setPopularity(popularity);
    }
}