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
        @JsonProperty("movieCd")
        private String movieCd;

        private String posterImageUrl;

        private String backDropImageUrl;

        private Double popularity;

        @JsonProperty("movieNm")
        private String movieNm;

        @JsonProperty("movieNmEn")
        private String movieNmEn;

        @JsonProperty("movieNmOg")
        private String movieNmOg;

        @JsonProperty("showTm")
        private String showTm;

        @JsonProperty("prdtYear")
        private String prdtYear;

        @JsonProperty("openDt")
        private String openDt;

        @JsonProperty("prdtStatNm")
        private String prdtStatNm;

        @JsonProperty("typeNm")
        private String typeNm;

        @JsonProperty("nations")
        private List<Nation> nations;

        @JsonProperty("genres")
        private List<Genre> genres;

        @JsonProperty("directors")
        private List<Director> directors;

        @JsonProperty("actors")
        private List<Actor> actors;

        @JsonProperty("showTypes")
        private List<ShowType> showTypes;

        @JsonProperty("companys")
        private List<Company> companies;

        @JsonProperty("audits")
        private List<Audit> audits;

        @JsonProperty("staffs")
        private List<Staff> staffs;

        private String overview;

        private List<Review> reivews;
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