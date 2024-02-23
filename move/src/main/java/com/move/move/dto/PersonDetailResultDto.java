package com.move.move.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonDetailResultDto {

    @JsonProperty("adult")
    private boolean adult;

    @JsonProperty("gender")
    private int gender;

    @JsonProperty("id")
    private int id;

    @JsonProperty("known_for_department")
    private String knownForDepartment;

    @JsonProperty("name")
    private String name;

    @JsonProperty("original_name")
    private String originalName;

    @JsonProperty("popularity")
    private double popularity;

    @JsonProperty("profile_path")
    private String profilePath;

    @JsonProperty("known_for")
    private List<KnownFor> knownFor;

    @Getter
    @Setter
    public static class KnownFor {

        @JsonProperty("adult")
        private boolean adult;

        @JsonProperty("backdrop_path")
        private String backdropPath;

        @JsonProperty("id")
        private int id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("original_language")
        private String originalLanguage;

        @JsonProperty("original_title")
        private String originalTitle;

        @JsonProperty("overview")
        private String overview;

        @JsonProperty("poster_path")
        private String posterPath;

        @JsonProperty("media_type")
        private String mediaType;

        @JsonProperty("genre_ids")
        private List<Integer> genreIds;

    }
}
