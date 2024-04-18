package com.move.move.dailyboxoffice.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DailyBoxOfficeRequest {

    private String key;

    private String targetDt;

    private String itemPerPage;

    private String multiMovieYn;

    private String repNationCd;

    private String wideAreaCd;
}
