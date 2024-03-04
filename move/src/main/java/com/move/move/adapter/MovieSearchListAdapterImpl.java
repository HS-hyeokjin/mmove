package com.move.move.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.move.move.dto.MovieSearchListRequestDto;
import com.move.move.dto.MovieSearchListResponseDto;
import com.move.move.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    @Override
    public MovieSearchListResponseDto getMovieSearchList(MovieSearchListRequestDto movieSearchListRequestDto) {
        try {
            String encodedMovieName = URLEncoder.encode(movieSearchListRequestDto.getMovieNm(), "UTF-8");
            String encodedDirectorName = URLEncoder.encode(movieSearchListRequestDto.getDirectorNm(), "UTF-8");

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("key", apiKey)
                    .queryParam("movieNm", encodedMovieName)
                    .queryParam("directorNm", encodedDirectorName)
                    .queryParam("curPage", movieSearchListRequestDto.getCurPage());

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    null,
                    String.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String responseBody = responseEntity.getBody();
                    MovieSearchListResponseDto movieSearchListResponseDto = objectMapper.readValue(responseBody, MovieSearchListResponseDto.class);
                    return movieSearchListResponseDto;
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ApiRequestException("API 응답 매핑 실패", e);
                }
            } else {
                throw new ApiRequestException("API 요청 실패. 상태 코드: " + responseEntity.getStatusCodeValue());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ApiRequestException("URL 인코딩 실패", e);
        }
    }
}
