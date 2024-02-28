package com.move.move.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSearchListRequestDto {

    private String curPage;
    private String itemPerPage;
    private String movieNm;
    private String directorNm;
    private String openStartDt;
    private String openEndDt;
    private String prdtStartYear;
    private String prdtEndYear;

}
