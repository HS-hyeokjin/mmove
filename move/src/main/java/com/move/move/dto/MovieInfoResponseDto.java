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

        @JsonProperty("source")
        private String source;
    }

    @Getter
    @Setter
    public static class MovieInfo {
        @JsonProperty("movieCd")
        private String movieCd;

        private String imageUrl;

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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        MovieInfoResult movieInfoResult = this.getMovieInfoResult();
        if (movieInfoResult != null) {
            MovieInfo movieInfo = movieInfoResult.getMovieInfo();
            if (movieInfo != null) {
                result.append("Movie Code: ").append(movieInfo.getMovieCd()).append("\n");
                result.append("Movie Name: ").append(movieInfo.getMovieNm()).append("\n");
                result.append("Movie Name (English): ").append(movieInfo.getMovieNmEn()).append("\n");
                result.append("Movie Name (Original): ").append(movieInfo.getMovieNmOg()).append("\n");
                result.append("Show Time: ").append(movieInfo.getShowTm()).append("\n");
                result.append("Production Year: ").append(movieInfo.getPrdtYear()).append("\n");
                result.append("Open Date: ").append(movieInfo.getOpenDt()).append("\n");
                result.append("Production Status: ").append(movieInfo.getPrdtStatNm()).append("\n");
                result.append("Type: ").append(movieInfo.getTypeNm()).append("\n");

                // Print Nations
                List<Nation> nations = movieInfo.getNations();
                result.append("Nations: ");
                appendList(result, nations, Nation::getNationNm);

                // Print Genres
                List<Genre> genres = movieInfo.getGenres();
                result.append("Genres: ");
                appendList(result, genres, Genre::getGenreNm);

                // ... (다른 리스트 필드들에 대한 출력)

                // Print Source
                result.append("Source: ").append(movieInfoResult.getSource()).append("\n");
            }
        }

        return result.toString();
    }

    private <T> void appendList(StringBuilder result, List<T> list, java.util.function.Function<T, String> mapper) {
        if (list != null) {
            list.forEach(item -> result.append(mapper.apply(item)).append(", "));
        }
        result.append("\n");
    }
}