package com.move.move.service;

import com.move.move.dto.PersonSearchListResponseDto;

public interface PersonSearchListService {

    PersonSearchListResponseDto getPersonSearchList(String personName, String filmoName);

}
