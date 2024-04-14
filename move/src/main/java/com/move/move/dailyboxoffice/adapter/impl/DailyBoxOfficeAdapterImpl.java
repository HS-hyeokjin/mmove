package com.move.move.dailyboxoffice.adapter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.move.move.dailyboxoffice.adapter.DailyBoxOfficeAdapter;
import com.move.move.dailyboxoffice.dto.DailyBoxOfficeRequest;
import com.move.move.dailyboxoffice.dto.DailyBoxOfficeResponse;
import com.move.move.exception.ApiRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@Component
public class DailyBoxOfficeAdapterImpl implements DailyBoxOfficeAdapter {

    @Value("${daily-box-office.api.url}")
    private String url;

    @Value("${kofic.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public DailyBoxOfficeAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "dailyBoxOfficeCache", key = "#dailyBoxOfficeRequest.targetDt")
    @Override
    public DailyBoxOfficeResponse getDailyBoxOfficeData(DailyBoxOfficeRequest dailyBoxOfficeRequest) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("key", apiKey)
                .queryParam("targetDt", dailyBoxOfficeRequest.getTargetDt())
                .queryParam("repNationCd", dailyBoxOfficeRequest.getRepNationCd());
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
                DailyBoxOfficeResponse dailyBoxOfficeResponse = objectMapper.readValue(responseBody, DailyBoxOfficeResponse.class);
                return dailyBoxOfficeResponse;
            } catch (IOException e) {
                String errorMessage = "API 응답 매핑 실패: " + e.getMessage();
                throw new ApiRequestException(errorMessage, e);
            }
        } else {
            String errorMessage = "API 요청 실패. 상태 코드: " + responseEntity.getStatusCode().value();
            throw new ApiRequestException(errorMessage);
        }
    }
}