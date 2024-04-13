package com.move.move.searchperson.adapter;

import com.move.move.searchperson.dto.PersonSearchListRequestDto;
import com.move.move.searchperson.dto.PersonSearchListResponseDto;

public interface PersonSearchListAdapter {

    PersonSearchListResponseDto getPersonSearchList(PersonSearchListRequestDto personSearchListRequestDto);
}