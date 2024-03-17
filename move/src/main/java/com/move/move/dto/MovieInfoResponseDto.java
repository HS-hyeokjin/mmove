package com.move.move.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.move.move.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieInfoResponseDto {

    @JsonProperty("movieInfoResult")
    private MovieInfoResult movieInfoResult;

    @Getter
    @Setter
    public static class MovieInfoResult {
        @JsonProperty("movieInfo")
        private MovieInfo movieInfo;

        @JsonProperty("source")
        private String source;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieInfo {
        private String movieCd;

        private String posterImageUrl;

        private String backDropImageUrl;

        private Double popularity;

        private String movieNm;

        private String movieNmEn;

        private String movieNmOg;

        private String showTm;

        private String prdtYear;

        private String openDt;

        private String prdtStatNm;

        private String typeNm;

        private List<Nation> nations;

        private List<Genre> genres;

        private List<Director> directors;

        private List<Actor> actors;

        private List<ShowType> showTypes;

        @JsonProperty("companys")
        private List<Company> companies;

        private List<Audit> audits;

        private List<Staff> staffs;

        private String overview;

    }

    @Getter
    @Setter
    public static class Nation {
        private String nationNm;
    }

    @Getter
    @Setter
    public static class Genre {
        private String genreNm;
    }

    @Getter
    @Setter
    public static class Director {
        private String peopleNm;
        private String peopleNmEn;
    }

    @Getter
    @Setter
    public static class Actor {
        private String peopleNm;
        private String peopleNmEn;
        private String peopleImageUrl;
        private String cast;
        private String castEn;
    }

    @Getter
    @Setter
    public static class ShowType {
        private String showTypeGroupNm;
        private String showTypeNm;
    }

    @Getter
    @Setter
    public static class Company {
        private String companyCd;
        private String companyNm;
        private String companyNmEn;
        private String companyPartNm;
    }

    @Getter
    @Setter
    public static class Audit {
        private String auditNo;
        private String watchGradeNm;
    }

    @Getter
    @Setter
    public static class Staff {
        private String peopleNm;
        private String peopleNmEn;
        private String staffRoleNm;
    }

}