package com.move.move.search.searchmovie.adapter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.move.move.search.searchmovie.adapter.MovieSearchListAdapter;
import com.move.move.search.searchmovie.dto.MovieSearchListRequest;
import com.move.move.search.searchmovie.dto.MovieSearchListResponse;
import com.move.move.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * 영화 검색 목록을 가져오기 위한 어댑터
 */
@Component
public class MovieSearchListAdapterImpl implements MovieSearchListAdapter {

    @Value("${movie-search-list.api.url}")
    private String url;

    @Value("${kofic.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public MovieSearchListAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 영화 검색 목록을 가져오는 메서드
     * UTF-8 문자열 요청
     *
     * @param movieSearchListRequest 영화 검색 목록 요청
     * @return 영화 검색 목록 응답
     * @throws ApiRequestException API 요청 실패 시 발생하는 예외
     */
    @Override
    public MovieSearchListResponse getMovieSearchList(MovieSearchListRequest movieSearchListRequest) {
        String requestUrl = url +
                "?key=" + apiKey +
                "&movieNm=" + movieSearchListRequest.getMovieNm() +
                "&directorNm=" + movieSearchListRequest.getDirectorNm() +
                "&curPage=" + movieSearchListRequest.getCurPage();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                null,
                String.class
        );

        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String responseBody = responseEntity.getBody();
                MovieSearchListResponse movieSearchListResponse = objectMapper.readValue(responseBody, MovieSearchListResponse.class);
                return movieSearchListResponse;
            } catch (IOException e) {
                throw new ApiRequestException("API 응답 매핑 실패", e);
            }
        } else {
            throw new ApiRequestException("API 요청 실패. 상태 코드: " + responseEntity.getStatusCode().value());
        }
    }
}
