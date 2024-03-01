package com.move.move.service;

import com.move.move.adapter.PersonSearchListAdapter;
import com.move.move.dto.PersonSearchListRequestDto;
import com.move.move.dto.PersonSearchListResponseDto;
import org.springframework.stereotype.Service;

@Service
public class PersonSearchListServiceImpl implements PersonSearchListService {

    private final PersonSearchListAdapter personSearchListAdapter;

    public PersonSearchListServiceImpl(PersonSearchListAdapter personSearchListAdapter) {
        this.personSearchListAdapter = personSearchListAdapter;
    }

    @Override
    public PersonSearchListResponseDto getPersonSearchList(String personName, String filmoName) {
        PersonSearchListRequestDto personSearchListRequestDto = new PersonSearchListRequestDto();
        personSearchListRequestDto.setPeopleNm(personName);
        personSearchListRequestDto.setFilmoNames(filmoName);
        return personSearchListAdapter.getPersonSearchList(personSearchListRequestDto);
    }
}
