package com.move.move.searchperson.service.impl;

import com.move.move.personinfo.adapter.PersonDetailAdapter;
import com.move.move.searchperson.adapter.PersonSearchListAdapter;
import com.move.move.personinfo.dto.PersonDetailRequestDto;
import com.move.move.personinfo.dto.PersonDetailResponseDto;
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
            PersonDetailRequestDto personDetailRequestDto = new PersonDetailRequestDto();
            personDetailRequestDto.setPersonEnNm(person.getPeopleNmEn());
            PersonDetailResponseDto personDetailResponseDto = personDetailAdapter.getPersonDetail(personDetailRequestDto);

            if (personDetailResponseDto != null) {
                person.setProfileImg(personDetailResponseDto.getPersonDetailResultDto().getProfilePath());
            }
        }
        return personSearchListResponseDto;
    }
}