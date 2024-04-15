package com.move.move.weeklyboxoffice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeeklyBoxOfficeResponse {

        private BoxOfficeResult boxOfficeResult;

        @Getter
        @Setter
        public static class BoxOfficeResult {

            private String boxofficeType;

            private String showRange;

            private String yearWeekTime;

            private List<WeeklyBoxOffice> weeklyBoxOfficeList;
        }

        @Getter
        @Setter
        public static class WeeklyBoxOffice {

            private String rnum;

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