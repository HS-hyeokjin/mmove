package com.move.move.search.searchperson.adapter;

import com.move.move.search.searchperson.dto.PersonSearchListRequest;
import com.move.move.search.searchperson.dto.PersonSearchListResponseDto;

public interface PersonSearchListAdapter {

    PersonSearchListResponseDto getPersonSearchList(PersonSearchListRequest personSearchListRequest);
}