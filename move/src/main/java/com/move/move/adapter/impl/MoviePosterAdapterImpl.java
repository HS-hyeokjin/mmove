package com.move.move.adapter.impl;

import com.move.move.adapter.MoviePosterAdapter;
import com.move.move.dto.MoviePosterResponseDto;
import com.move.move.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MoviePosterAdapterImpl implements MoviePosterAdapter {

    @Value("${tmdb.movie.api.url}")
    private String apiUrl;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.image.url}")
    private String imageUrl;

    private final RestTemplate restTemplate;

    public MoviePosterAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String searchMoviePoster(String movieTitle) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("api_key", apiKey)
                    .queryParam("query", movieTitle);
            ResponseEntity<MoviePosterResponseDto> responseEntity = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    null,
                    MoviePosterResponseDto.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                MoviePosterResponseDto moviePosterResponseDto = responseEntity.getBody();
                if (moviePosterResponseDto != null && moviePosterResponseDto.getResults() != null && !moviePosterResponseDto.getResults().isEmpty()) {
                    String posterUrl = imageUrl + moviePosterResponseDto.getResults().get(0).getPoster();
                    return posterUrl;
                }
            }
            return null;
        } catch (Exception e) {
            throw new ApiRequestException("영화 포스터 요청 오류", e);
        }
    }
}