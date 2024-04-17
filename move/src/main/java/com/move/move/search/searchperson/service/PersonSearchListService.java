package com.move.move.search.searchperson.service;

import com.move.move.search.searchperson.dto.PersonSearchListResponseDto;

public interface PersonSearchListService {

    PersonSearchListResponseDto getPersonSearchList(String personName, String filmoName);

}
