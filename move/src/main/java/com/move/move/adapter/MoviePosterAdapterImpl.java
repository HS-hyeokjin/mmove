package com.move.move.adapter;

import com.move.move.dto.MoviePosterResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MoviePosterAdapterImpl implements MoviePosterAdapter {

    @Value("${tmdb.api.url}")
    private String apiUrl;

    @Value("${image.api.key}")
    private String apiKey;

    @Value("${tmdb.image.url}")
    private String imageUrl;

    private final RestTemplate restTemplate;

    public MoviePosterAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String searchMoviePoster(String movieTitle) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("api_key", apiKey)
                .queryParam("query", movieTitle);
        System.out.println(builder.toUriString());
        ResponseEntity<MoviePosterResponseDto> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                MoviePosterResponseDto.class
        );

        System.out.println(responseEntity.getStatusCode().toString());
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            MoviePosterResponseDto moviePosterResponseDto = responseEntity.getBody();
            if (moviePosterResponseDto != null && moviePosterResponseDto.getResults() != null && !moviePosterResponseDto.getResults().isEmpty()) {
                String posterUrl = imageUrl + moviePosterResponseDto.getResults().get(0).getPoster();
                System.out.println(posterUrl);
                return posterUrl;
            }
        }

        return "1";
    }
}