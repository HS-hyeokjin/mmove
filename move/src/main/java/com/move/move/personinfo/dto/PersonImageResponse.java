package com.move.move.personinfo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonImageResponse {

    private List<PersonSearchResult> results;

    @Getter
    @Setter
    public static class PersonSearchResult{

        @JsonProperty("profile_path")
        private String profileUrl;
    }
}
