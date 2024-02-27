package com.move.move.service;

import com.move.move.adapter.PersonDetailAdapter;
import com.move.move.dto.PersonDetailRequestDto;
import com.move.move.dto.PersonDetailResponseDto;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailServiceImpl implements PersonDetailService{

    private final PersonDetailAdapter personDetailAdapter;

    public PersonDetailServiceImpl(PersonDetailAdapter personDetailAdapter) {
        this.personDetailAdapter = personDetailAdapter;
    }


    @Override
    public PersonDetailResponseDto getPersonDetail(String personName) {
        PersonDetailRequestDto personDetailRequestDto = new PersonDetailRequestDto();
        personDetailRequestDto.setPersonEnNm(personName);
        return personDetailAdapter.getPersonDetail(personDetailRequestDto);
    }
}
