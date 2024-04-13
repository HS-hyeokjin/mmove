package com.move.move.personinfo.service.impl;

import com.move.move.personinfo.adapter.PersonDetailAdapter;
import com.move.move.personinfo.dto.PersonDetailRequestDto;
import com.move.move.personinfo.dto.PersonDetailResponseDto;
import com.move.move.personinfo.service.PersonDetailService;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailServiceImpl implements PersonDetailService {

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
