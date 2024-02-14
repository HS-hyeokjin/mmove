package com.move.move.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DailyBoxOfficeRequestDto {
    private String key;
    private String targetDt;
    private String itemPerPage = "10";
    private String multiMovieYn;
    private String repNationCd;
    private String wideAreaCd;
}
