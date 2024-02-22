package com.move.move.adapter;

import com.move.move.dto.PersonImageResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PersonImageAdapterImpl implements PersonImageAdapter {

    @Value("${tmdb.person.api.url}")
    private String apiUrl;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.image.url}")
    private String imageUrl;

    private final RestTemplate restTemplate;

    public PersonImageAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String personImageUrl(String personName) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("api_key", apiKey)
                .queryParam("query", personName);
        ResponseEntity<PersonImageResponseDto> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                PersonImageResponseDto.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            PersonImageResponseDto personImageResponseDto = responseEntity.getBody();
            if (personImageResponseDto != null && personImageResponseDto.getResults() != null && !personImageResponseDto.getResults().isEmpty()) {
                String posterUrl = imageUrl + personImageResponseDto.getResults().get(0).getProfileUrl();
                return posterUrl;
            }
        }
        return "1";
    }
}
