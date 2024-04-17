package com.move.move.search.searchperson.service.impl;

import com.move.move.personinfo.adapter.PersonDetailAdapter;
import com.move.move.search.searchperson.adapter.PersonSearchListAdapter;
import com.move.move.personinfo.dto.PersonDetailRequest;
import com.move.move.personinfo.dto.PersonDetailResponse;
import com.move.move.search.searchperson.dto.PersonSearchListRequest;
import com.move.move.search.searchperson.dto.PersonSearchListResponseDto;
import com.move.move.search.searchperson.service.PersonSearchListService;
import org.springframework.stereotype.Service;

/**
 * 인물 검색 목록 서비스의 구현체
 */
@Service
public class PersonSearchListServiceImpl implements PersonSearchListService {

    private final PersonSearchListAdapter personSearchListAdapter;
    private final PersonDetailAdapter personDetailAdapter;

    public PersonSearchListServiceImpl(PersonSearchListAdapter personSearchListAdapter, PersonDetailAdapter personDetailAdapter) {
        this.personSearchListAdapter = personSearchListAdapter;
        this.personDetailAdapter = personDetailAdapter;
    }

    /**
     * 인물 검색 목록을 가져오는 메서드
     *
     * @param personName 검색할 인물의 이름
     * @param filmoName 검색할 인물의 필모그래피
     * @return 인물 검색 목록 응답 DTO
     */
    @Override
    public PersonSearchListResponseDto getPersonSearchList(String personName, String filmoName) {
        PersonSearchListRequest personSearchListRequest = new PersonSearchListRequest();
        personSearchListRequest.setPeopleNm(personName);
        personSearchListRequest.setFilmoNames(filmoName);
        PersonSearchListResponseDto personSearchListResponseDto = personSearchListAdapter.getPersonSearchList(personSearchListRequest);

        for (PersonSearchListResponseDto.PeopleDto person : personSearchListResponseDto.getPeopleListResult().getPeopleList()) {
            PersonDetailRequest personDetailRequest = new PersonDetailRequest();
            personDetailRequest.setPersonEnNm(person.getPeopleNmEn());
            PersonDetailResponse personDetailResponse = personDetailAdapter.getPersonDetail(personDetailRequest);

            if (personDetailResponse != null) {
                person.setProfileImg(personDetailResponse.getPersonDetailResult().getProfilePath());
            }
        }
        return personSearchListResponseDto;
    }
}