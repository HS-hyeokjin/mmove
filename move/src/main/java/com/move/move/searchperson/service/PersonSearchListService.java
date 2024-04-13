package com.move.move.searchperson.service;

import com.move.move.searchperson.dto.PersonSearchListResponseDto;

public interface PersonSearchListService {

    PersonSearchListResponseDto getPersonSearchList(String personName, String filmoName);

}
