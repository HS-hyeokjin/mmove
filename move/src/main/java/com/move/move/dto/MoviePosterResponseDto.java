package com.move.move.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoviePosterResponseDto {

    @JsonProperty("Search")
    private List<MovieSearchResult> search;

    @Getter
    @Setter
    public static class MovieSearchResult {

        @JsonProperty("Poster")
        private String poster;
    }
}