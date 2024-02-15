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

    public void printDetails() {
        System.out.println("BoxOfficeResult:");
        System.out.println("  boxofficeType: " + boxOfficeResult.getBoxofficeType());
        System.out.println("  showRange: " + boxOfficeResult.getShowRange());

        List<DailyBoxOffice> dailyBoxOfficeList = boxOfficeResult.getDailyBoxOfficeList();
        System.out.println("  dailyBoxOfficeList:");

        for (DailyBoxOffice dailyBoxOffice : dailyBoxOfficeList) {
            System.out.println("    DailyBoxOffice:");
            System.out.println("      rnum: " + dailyBoxOffice.getRnum());
            System.out.println("      rank: " + dailyBoxOffice.getRank());
            System.out.println("      rankInten: " + dailyBoxOffice.getRankInten());
            System.out.println("      rankOldAndNew: " + dailyBoxOffice.getRankOldAndNew());
            System.out.println("      movieCd: " + dailyBoxOffice.getMovieCd());
            System.out.println("      movieNm: " + dailyBoxOffice.getMovieNm());
            System.out.println("      openDt: " + dailyBoxOffice.getOpenDt());
            System.out.println("      salesAmt: " + dailyBoxOffice.getSalesAmt());
            System.out.println("      salesShare: " + dailyBoxOffice.getSalesShare());
            System.out.println("      salesInten: " + dailyBoxOffice.getSalesInten());
            System.out.println("      salesChange: " + dailyBoxOffice.getSalesChange());
            System.out.println("      salesAcc: " + dailyBoxOffice.getSalesAcc());
            System.out.println("      audiCnt: " + dailyBoxOffice.getAudiCnt());
            System.out.println("      audiInten: " + dailyBoxOffice.getAudiInten());
            System.out.println("      audiChange: " + dailyBoxOffice.getAudiChange());
            System.out.println("      audiAcc: " + dailyBoxOffice.getAudiAcc());
            System.out.println("      scrnCnt: " + dailyBoxOffice.getScrnCnt());
            System.out.println("      showCnt: " + dailyBoxOffice.getShowCnt());
            System.out.println("      imageUrl: " + dailyBoxOffice.getImageUrl());
        }
    }
}
