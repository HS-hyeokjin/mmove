package com.move.move.adapter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.move.move.adapter.PersonSearchListAdapter;
import com.move.move.dto.PersonSearchListRequestDto;
import com.move.move.dto.PersonSearchListResponseDto;
import com.move.move.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class PersonSearchListAdapterImpl implements PersonSearchListAdapter {

    @Value("person-search-list.api.url")
    private String url;

    @Value("kofic.api.key")
    private String apiKey;

    private final RestTemplate restTemplate;

    public PersonSearchListAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PersonSearchListResponseDto getPersonSearchList(PersonSearchListRequestDto personSearchListRequestDto) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("key", apiKey)
                .queryParam("peopleNm", personSearchListRequestDto.getPeopleNm())
                .queryParam("filmoNames", personSearchListRequestDto.getFilmoNames());

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
                PersonSearchListResponseDto personSearchListResponseDto = objectMapper.readValue(responseBody, PersonSearchListResponseDto.class);
                return personSearchListResponseDto;
            } catch (IOException e) {
                e.printStackTrace();
                throw new ApiRequestException("API 응답 매핑 실패", e);
            }
        } else {
            throw new ApiRequestException("API 요청 실패. 상태 코드: " + responseEntity.getStatusCodeValue());
        }
    }
}