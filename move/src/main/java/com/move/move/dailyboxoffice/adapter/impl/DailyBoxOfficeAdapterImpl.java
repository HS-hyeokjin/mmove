package com.move.move.dailyboxoffice.adapter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.move.move.dailyboxoffice.adapter.DailyBoxOfficeAdapter;
import com.move.move.dailyboxoffice.dto.DailyBoxOfficeRequest;
import com.move.move.dailyboxoffice.dto.DailyBoxOfficeResponse;
import com.move.move.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

/**
 * kofic으로 일일 박스 오피스 데이터를 요청하기 위한 아답터
 *
 */
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

    /**
     * 일일 박스 오피스 데이터를 요청하는 메서드
     *
     * @param dailyBoxOfficeRequest DailyBoxOfficeRequest 객체
     * @return DailyBoxOfficeResponse 객체
     */
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

        return mapResponse(responseEntity);
    }

    /**
     * HTTP 응답을 DailyBoxOfficeResponse 으로 매핑
     *
     * @param responseEntity ResponseEntity 객체
     * @return DailyBoxOfficeResponse 객체
     */
    private DailyBoxOfficeResponse mapResponse(ResponseEntity<String> responseEntity) {
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String responseBody = responseEntity.getBody();
                return objectMapper.readValue(responseBody, DailyBoxOfficeResponse.class);
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