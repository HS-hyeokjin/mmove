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
public class MovieImageAdapterImpl implements MovieImageAdapter {

    @Value("${OMDb.api.url}")
    private String apiUrl;

    @Value("${image.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public MovieImageAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String searchMoviePoster(String movieName) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("apikey", apiKey)
                .queryParam("s", movieName);

        ResponseEntity<MoviePosterResponseDto> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                MoviePosterResponseDto.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            MoviePosterResponseDto moviePosterResponseDto = responseEntity.getBody();
            if (moviePosterResponseDto != null && moviePosterResponseDto.getSearch() != null && !moviePosterResponseDto.getSearch().isEmpty()) {
                String posterUrl = moviePosterResponseDto.getSearch().get(0).getPoster();
                System.out.println(posterUrl);
                return posterUrl;
            }
        }

        return "1";
    }
}