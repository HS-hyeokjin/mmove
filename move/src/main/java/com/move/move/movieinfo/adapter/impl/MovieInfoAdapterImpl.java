package com.move.move.movieinfo.adapter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.move.move.movieinfo.adapter.MovieInfoAdapter;
import com.move.move.movieinfo.dto.MovieInfoResponseDto;
import com.move.move.exception.ApiRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@Component
public class MovieInfoAdapterImpl implements MovieInfoAdapter {

    @Value("${movie-info.api.url}")
    private String url;

    @Value("${kofic.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public MovieInfoAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MovieInfoResponseDto getMovieInfo(String movieCode) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("key", apiKey)
                .queryParam("movieCd", movieCode);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                String.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String responseBody = responseEntity.getBody();
                MovieInfoResponseDto movieInfoResponseDto = objectMapper.readValue(responseBody, MovieInfoResponseDto.class);
                return movieInfoResponseDto;
            } catch (IOException e) {
                throw new ApiRequestException("API 응답 매핑 실패", e);
            }
        } else {
            throw new ApiRequestException("API 요청 실패. 상태 코드: " + responseEntity.getStatusCode().value());
        }
    }
}
