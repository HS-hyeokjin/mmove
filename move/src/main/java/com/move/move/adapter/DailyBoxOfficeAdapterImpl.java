package com.move.move.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.move.move.dto.DailyBoxOfficeRequestDto;
import com.move.move.dto.DailyBoxOfficeResponseDto;
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
public class DailyBoxOfficeAdapterImpl implements DailyBoxOfficeAdapter {

    @Value("${daily-box-office.api.url}")
    private String url;

    @Value("${kofic.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public DailyBoxOfficeAdapterImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public DailyBoxOfficeResponseDto getDailyBoxOfficeData(DailyBoxOfficeRequestDto dailyBoxOfficeRequestDto) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("key", apiKey)
                .queryParam("targetDt", dailyBoxOfficeRequestDto.getTargetDt())
                .queryParam("repNationCd", dailyBoxOfficeRequestDto.getRepNationCd());
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
                DailyBoxOfficeResponseDto dailyBoxOfficeResponseDto = objectMapper.readValue(responseBody, DailyBoxOfficeResponseDto.class);
                return dailyBoxOfficeResponseDto;
            } catch (IOException e) {
                e.printStackTrace();
                throw new ApiRequestException("API 응답 매핑 실패", e);
            }
        } else {
            throw new ApiRequestException("API 요청 실패. 상태 코드: " + responseEntity.getStatusCodeValue());
        }
    }
}
