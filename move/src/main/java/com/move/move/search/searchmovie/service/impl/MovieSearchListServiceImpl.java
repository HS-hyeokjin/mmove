package com.move.move.search.searchmovie.service.impl;

import com.move.move.movieinfo.adapter.MovieDetailAdapter;
import com.move.move.search.searchmovie.adapter.MovieSearchListAdapter;
import com.move.move.search.searchmovie.dto.MovieSearchListRequest;
import com.move.move.search.searchmovie.dto.MovieSearchListResponse;
import com.move.move.search.searchmovie.service.MovieSearchListService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/**
 * 영화 검색 서비스
 */
@Service
public class MovieSearchListServiceImpl implements MovieSearchListService {

    private final MovieSearchListAdapter movieSearchListAdapter;
    private final MovieDetailAdapter movieDetailAdapter;

    public MovieSearchListServiceImpl(MovieSearchListAdapter movieSearchListAdapter, MovieDetailAdapter movieDetailAdapter) {
        this.movieSearchListAdapter = movieSearchListAdapter;
        this.movieDetailAdapter = movieDetailAdapter;
    }

    /**
     * 영화 검색 목록을 가져오는 메서드
     *
     * @param movieName 영화 제목
     * @param directorName 감독 이름
     * @param curPage 현재 페이지
     * @return 영화 검색 목록 응답
     * @throws UnsupportedEncodingException 인코딩 예외
     */
    @Override
    public MovieSearchListResponse getMovieSearchList(String movieName, String directorName, String curPage) throws UnsupportedEncodingException {
        MovieSearchListRequest movieSearchListRequest = new MovieSearchListRequest();
        movieSearchListRequest.setMovieNm(movieName);
        movieSearchListRequest.setDirectorNm(directorName);
        movieSearchListRequest.setCurPage(curPage);

        MovieSearchListResponse movieSearchListResponse = movieSearchListAdapter.getMovieSearchList(movieSearchListRequest);

        updateMoviePosters(movieSearchListResponse);

        return movieSearchListResponse;
    }

    /**
     * 영화 포스터 URL을 업데이트하는 메서드
     * @param movieSearchListResponse 영화 검색 목록 응답
     */
    private void updateMoviePosters(MovieSearchListResponse movieSearchListResponse) {
        List<MovieSearchListResponse.MovieDTO> movieDTOList = movieSearchListResponse.getMovieListResult().getMovieList();
        Iterator<MovieSearchListResponse.MovieDTO> iterator = movieDTOList.iterator();
        while (iterator.hasNext()) {
            MovieSearchListResponse.MovieDTO movieDTO = iterator.next();
            String movieTitle = movieDTO.getMovieNm();
            String posterUrl = movieDetailAdapter.searchMoviePoster(movieTitle);
            if (posterUrl == null) {iterator.remove();}
                movieDTO.setPosterUrl(posterUrl);
            }
        }
    }