package com.move.move.searchperson.service.impl;

import com.move.move.personinfo.adapter.PersonDetailAdapter;
import com.move.move.searchperson.adapter.PersonSearchListAdapter;
import com.move.move.personinfo.dto.PersonDetailRequest;
import com.move.move.personinfo.dto.PersonDetailResponse;
import com.move.move.searchperson.dto.PersonSearchListRequestDto;
import com.move.move.searchperson.dto.PersonSearchListResponseDto;
import com.move.move.searchperson.service.PersonSearchListService;
import org.springframework.stereotype.Service;

@Service
public class PersonSearchListServiceImpl implements PersonSearchListService {

    private final PersonSearchListAdapter personSearchListAdapter;
    private final PersonDetailAdapter personDetailAdapter;

    public PersonSearchListServiceImpl(PersonSearchListAdapter personSearchListAdapter, PersonDetailAdapter personDetailAdapter) {
        this.personSearchListAdapter = personSearchListAdapter;
        this.personDetailAdapter = personDetailAdapter;
    }

    @Override
    public PersonSearchListResponseDto getPersonSearchList(String personName, String filmoName) {
        PersonSearchListRequestDto personSearchListRequestDto = new PersonSearchListRequestDto();
        personSearchListRequestDto.setPeopleNm(personName);
        personSearchListRequestDto.setFilmoNames(filmoName);
        PersonSearchListResponseDto personSearchListResponseDto = personSearchListAdapter.getPersonSearchList(personSearchListRequestDto);

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