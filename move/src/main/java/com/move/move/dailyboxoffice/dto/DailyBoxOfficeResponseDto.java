package com.move.move.dailyboxoffice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyBoxOfficeResponseDto {

    @JsonProperty("boxOfficeResult")
    private BoxOfficeResult boxOfficeResult;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BoxOfficeResult {

        private String showRange;

        private List<DailyBoxOffice> dailyBoxOfficeList;

        public void setShowRange(String showRange) {
            LocalDate startDate = LocalDate.parse(showRange.substring(0, 8), DateTimeFormatter.BASIC_ISO_DATE);
            this.showRange = startDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DailyBoxOffice {

        private String rank;

        private String rankInten;

        private String rankOldAndNew;

        private String movieCd;

        private String movieNm;

        private String openDt;

        private String salesAmt;

        private String salesShare;

        private String salesInten;

        private String salesChange;

        private String salesAcc;

        private String audiCnt;

        private String audiInten;

        private String audiChange;

        private String audiAcc;

        private String scrnCnt;

        private String showCnt;

        private String imageUrl;
    }

}
