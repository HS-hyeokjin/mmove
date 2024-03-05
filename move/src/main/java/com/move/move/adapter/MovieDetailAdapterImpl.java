package com.move.move.adapter;

import com.move.move.dto.MovieDetailResponseDto;
import com.move.move.dto.MovieDetailsResponseDto;
import com.move.move.dto.MoviePosterResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MovieDetailAdapterImpl implements MovieDetailAdapter{

    @Value("${tmdb.movie.api.url}")
    private String apiUrl;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.image.url}")
    private String imageUrl;

    private final RestTemplate restTemplate;

    public MovieDetailAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public MovieDetailResponseDto searchMovieDetail(String movieTitle) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("api_key", apiKey)
                .queryParam("query", movieTitle)
                .queryParam("language","ko");
        ResponseEntity<MovieDetailsResponseDto> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                MovieDetailsResponseDto.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null
                && !responseEntity.getBody().getResults().isEmpty()) {
            MovieDetailResponseDto movieDetailResponseDto = new MovieDetailResponseDto();
            movieDetailResponseDto.setMovieDetailResultDto(responseEntity.getBody().getResults().get(0));
            return movieDetailResponseDto;
        } else {
            return null;
        }
    }

    @Override
    public String searchMoviePoster(String movieTitle) {
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
    }
}
