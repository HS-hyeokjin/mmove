package com.move.move.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    }

    @Getter
    @Setter
    public static class MovieInfo {
        private String movieCd;
        private String movieNm;
        private String movieNmEn;
        private String movieNmOg;
        private String showTm;
        private String prdtYear;
        private String openDt;
        private String prdtStatNm;
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
        private String genres;
    }

    @Getter
    @Setter
    public static class Actor {
        private String genres;
    }

    @Getter
    @Setter
    public static class ShowType {
        private String showTypes;
    }
    @Getter
    @Setter
    public static class Company {
        private String genres;
    }
    @Getter
    @Setter
    public static class Audit {
        private String genres;
    }

    @Getter
    @Setter
    public static class Staff{
        private String staffs;
    }

}
