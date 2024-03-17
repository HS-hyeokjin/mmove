package com.move.move.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonSearchListRequestDto {

    private String key;

    private String curPage;

    private String itemPerPage;

    private String peopleNm;

    private String filmoNames;

}
