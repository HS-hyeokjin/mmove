package com.move.move.personinfo.adapter;

import com.move.move.personinfo.dto.PersonDetailRequestDto;
import com.move.move.personinfo.dto.PersonDetailResponseDto;

public interface PersonDetailAdapter {

    PersonDetailResponseDto getPersonDetail(PersonDetailRequestDto personDetailRequestDto);

    String personImageUrl(String personName);


}
