package com.move.move.search.searchperson.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonSearchListRequest {

    private String key;

    private String curPage;

    private String itemPerPage;

    private String peopleNm;

    private String filmoNames;

}
