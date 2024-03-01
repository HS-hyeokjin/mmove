package com.move.move.adapter;

import com.move.move.dto.PersonSearchListRequestDto;
import com.move.move.dto.PersonSearchListResponseDto;

public interface PersonSearchListAdapter {

    PersonSearchListResponseDto getPersonSearchList(PersonSearchListRequestDto personSearchListRequestDto);
}