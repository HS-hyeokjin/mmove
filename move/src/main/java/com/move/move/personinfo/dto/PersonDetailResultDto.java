package com.move.move.personinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonDetailResultDto {

    private boolean adult;

    private int gender;

    private int id;

    @JsonProperty("known_for_department")
    private String knownForDepartment;

    private String name;

    @JsonProperty("original_name")
    private String originalName;

    private double popularity;

    @JsonProperty("profile_path")
    private String profilePath;

    @JsonProperty("known_for")
    private List<KnownFor> knownFor;

    @Getter
    @Setter
    public static class KnownFor {

        private boolean adult;

        private String backdropPath;

        private int id;

        private String title;

        @JsonProperty("original_language")
        private String originalLanguage;

        @JsonProperty("original_title")
        private String originalTitle;

        private String overview;

        @JsonProperty("poster_path")
        private String posterPath;

        @JsonProperty("media_type")
        private String mediaType;

        @JsonProperty("genre_ids")
        private List<Integer> genreIds;

    }

}
