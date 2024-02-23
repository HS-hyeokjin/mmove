package com.move.move.adapter;

import com.move.move.dto.PersonDetailRequestDto;
import com.move.move.dto.PersonDetailResponseDto;

public interface PersonDetailAdapter {

    PersonDetailResponseDto getPersonDetail(PersonDetailRequestDto personDetailRequestDto);

    String personImageUrl(String personName);


}
