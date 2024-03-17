package com.move.move.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonImageResponseDto {

    private List<PersonSearchResult> results;

    @Getter
    @Setter
    public static class PersonSearchResult{

        @JsonProperty("profile_path")
        private String profileUrl;
    }
}
