package com.move.move.search.searchmovie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearchListResponse {

    private MovieListResult movieListResult;

    @Getter
    @Setter
    public static class MovieListResult {
        private String totCnt;
        private String source;
        private List<MovieDTO> movieList;
    }

    @Getter
    @Setter
    public static class MovieDTO {

        private String movieCd;
        private String movieNm;
        private String movieNmEn;
        private String prdtYear;
        private String openDt;
        private String typeNm;
        private String prdtStatNm;
        private String nationAlt;
        private String genreAlt;
        private String repNationNm;
        private String repGenreNm;
        private String posterUrl;
        private List<DirectorDTO> directors;
        private List<CompanyDTO> companys;

    }

    @Getter
    @Setter
    public static class DirectorDTO {
        private String peopleNm;

    }

    @Getter
    @Setter
    public static class CompanyDTO {
        private String companyCd;
        private String companyNm;
    }
}