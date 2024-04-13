package com.move.move.movieinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoviePosterResponseDto {

    @JsonProperty("results")
    private List<MovieSearchResult> results;

    @Getter
    @Setter
    public static class MovieSearchResult {

        @JsonProperty("poster_path")
        private String poster;
    }
}