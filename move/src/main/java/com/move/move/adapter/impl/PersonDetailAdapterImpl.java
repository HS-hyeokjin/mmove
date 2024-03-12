package com.move.move.adapter.impl;

import com.move.move.adapter.PersonDetailAdapter;
import com.move.move.dto.*;
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
    public PersonDetailResponseDto getPersonDetail(PersonDetailRequestDto personDetailRequestDto) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("api_key", apiKey)
                .queryParam("query", personDetailRequestDto.getPersonEnNm())
                .queryParam("language", "ko");

        ResponseEntity<PersonDetailsResponseDto> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                PersonDetailsResponseDto.class
        );


        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null
                && !responseEntity.getBody().getResults().isEmpty()) {
            PersonDetailResponseDto personDetailResponseDto = new PersonDetailResponseDto();
            personDetailResponseDto.setPersonDetailResultDto(responseEntity.getBody().getResults().get(0));
            return personDetailResponseDto;
        } else {
            return null;
        }
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
                if(posterUrl.equals("https://image.tmdb.org/t/p/w500null")){
                    return null;
                }
                return posterUrl;
            }
        }
        return null;
    }
}
