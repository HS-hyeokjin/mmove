package com.move.move.personinfo.adapter.impl;

import com.move.move.personinfo.adapter.PersonDetailAdapter;
import com.move.move.personinfo.dto.PersonDetailRequest;
import com.move.move.personinfo.dto.PersonDetailResponse;
import com.move.move.personinfo.dto.PersonDetailsResponse;
import com.move.move.personinfo.dto.PersonImageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * TMDB API, 인물 상세 정보를 요청하기 위한 어댑터
 */
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

    /**
     * 인물의 상세 정보를 가져오는 메서드
     * @param personDetailRequest 인물 상세 정보 요청 DTO
     * @return PersonDetailResponseDto 객체
     */
    @Override
    public PersonDetailResponse getPersonDetail(PersonDetailRequest personDetailRequest) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("api_key", apiKey)
                .queryParam("query", personDetailRequest.getPersonEnNm())
                .queryParam("language", "ko");

        ResponseEntity<PersonDetailsResponse> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                PersonDetailsResponse.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null
                && !responseEntity.getBody().getResults().isEmpty()) {
            PersonDetailResponse personDetailResponse = new PersonDetailResponse();
            personDetailResponse.setPersonDetailResult(responseEntity.getBody().getResults().get(0));
            return personDetailResponse;
        } else {
            return null;
        }
    }

    /**
     * 인물의 이미지 URL을 가져오는 메서드
     * @param personName 인물 이름
     * @return 인물 이미지 URL
     */
    @Override
    public String personImageUrl(String personName) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("api_key", apiKey)
                .queryParam("query", personName);
        ResponseEntity<PersonImageResponse> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                PersonImageResponse.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            PersonImageResponse personImageResponse = responseEntity.getBody();
            if (personImageResponse != null && personImageResponse.getResults() != null && !personImageResponse.getResults().isEmpty()) {
                String posterUrl = imageUrl + personImageResponse.getResults().get(0).getProfileUrl();
                if(posterUrl.equals("https://image.tmdb.org/t/p/w500null")){
                    return null;
                }
                return posterUrl;
            }
        }
        return null;
    }
}
