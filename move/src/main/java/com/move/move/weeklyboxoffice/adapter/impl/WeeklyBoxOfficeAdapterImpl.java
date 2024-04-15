package com.move.move.weeklyboxoffice.adapter.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.move.move.weeklyboxoffice.adapter.WeeklyBoxOfficeAdapter;
import com.move.move.weeklyboxoffice.dto.WeeklyBoxOfficeResponse;
import com.move.move.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

/**
 * kofic으로 주간 박스 오피스 데이터를 요청하기 위한 아답터
 */
@Component
public class WeeklyBoxOfficeAdapterImpl implements WeeklyBoxOfficeAdapter {


    @Value("${weekly-box-office.api.url}")
    private String url;

    @Value("${kofic.api.key}")
    private String apiKey;


    private final RestTemplate restTemplate;

    public WeeklyBoxOfficeAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 주간 박스 오피스 데이터를 가져오는 메서드
     *
     * @param date 조회할 날짜
     * @return WeeklyBoxOfficeResponseDto 객체
     */
    @Override
    public WeeklyBoxOfficeResponse getWeekBoxOffice(String date) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("key", apiKey)
                .queryParam("targetDt", date)
                .queryParam("WeekGb", "0");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                String.class
        );

        return mapResponse(responseEntity);
    }

    /**
     * HTTP 응답을 WeeklyBoxOfficeResponseDto로 매핑하는 메서드
     *
     * @param responseEntity ResponseEntity 객체
     * @return WeeklyBoxOfficeResponseDto 객체
     */
    private WeeklyBoxOfficeResponse mapResponse(ResponseEntity<String> responseEntity) {
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String responseBody = responseEntity.getBody();
                return objectMapper.readValue(responseBody, WeeklyBoxOfficeResponse.class);
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




