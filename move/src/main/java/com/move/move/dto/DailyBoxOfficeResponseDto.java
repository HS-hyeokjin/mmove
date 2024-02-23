package com.move.move.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyBoxOfficeResponseDto {

    @JsonProperty("boxOfficeResult")
    private BoxOfficeResult boxOfficeResult;

    @Getter
    @Setter
    public static class BoxOfficeResult {

        @JsonProperty("boxofficeType")
        private String boxofficeType;

        @JsonProperty("showRange")
        private String showRange;

        @JsonProperty("dailyBoxOfficeList")
        private List<DailyBoxOffice> dailyBoxOfficeList;

    }

    @Getter
    @Setter
    public static class DailyBoxOffice {

        @JsonProperty("rnum")
        private String rnum;

        @JsonProperty("rank")
        private String rank;

        @JsonProperty("rankInten")
        private String rankInten;

        @JsonProperty("rankOldAndNew")
        private String rankOldAndNew;

        @JsonProperty("movieCd")
        private String movieCd;

        @JsonProperty("movieNm")
        private String movieNm;

        @JsonProperty("openDt")
        private String openDt;

        @JsonProperty("salesAmt")
        private String salesAmt;

        @JsonProperty("salesShare")
        private String salesShare;

        @JsonProperty("salesInten")
        private String salesInten;

        @JsonProperty("salesChange")
        private String salesChange;

        @JsonProperty("salesAcc")
        private String salesAcc;

        @JsonProperty("audiCnt")
        private String audiCnt;

        @JsonProperty("audiInten")
        private String audiInten;

        @JsonProperty("audiChange")
        private String audiChange;

        @JsonProperty("audiAcc")
        private String audiAcc;

        @JsonProperty("scrnCnt")
        private String scrnCnt;

        @JsonProperty("showCnt")
        private String showCnt;

        private String imageUrl;
    }

}
