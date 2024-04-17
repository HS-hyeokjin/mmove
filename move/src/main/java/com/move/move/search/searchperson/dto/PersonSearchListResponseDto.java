package com.move.move.search.searchperson.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonSearchListResponseDto {

    private PeopleListResult peopleListResult;

    @Getter
    @Setter
    public static class PeopleListResult {
        private String totCnt;
        private List<PeopleDto> peopleList;
        private String source;
    }

    @Getter
    @Setter
    public static class PeopleDto {
        private String peopleCd;
        private String peopleNm;
        private String peopleNmEn;
        private String repRoleNm;
        private String filmoNames;
        private String profileImg;
    }
}

