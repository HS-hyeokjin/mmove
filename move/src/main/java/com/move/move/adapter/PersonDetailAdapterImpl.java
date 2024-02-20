package com.move.move.adapter;

import com.move.move.dto.MoviePosterResponseDto;
import com.move.move.dto.PersonDetailResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PersonDetailAdapterImpl implements PersonDetailAdapter {

    @Value("${tmdb.person.api.url}")
    private String apiUrl;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.image.url}")
    private String imageUrl;

    private final RestTemplate restTemplate;

    public PersonDetailAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String personImageUrl(String personName) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("api_key", apiKey)
                .queryParam("query", personName);
        ResponseEntity<PersonDetailResponseDto> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                PersonDetailResponseDto.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            PersonDetailResponseDto personDetailResponseDto = responseEntity.getBody();
            if (personDetailResponseDto != null && personDetailResponseDto.getResults() != null && !personDetailResponseDto.getResults().isEmpty()) {
                String posterUrl = imageUrl + personDetailResponseDto.getResults().get(0).getProfileUrl();
                return posterUrl;
            }
        }
        return "1";
    }
}
