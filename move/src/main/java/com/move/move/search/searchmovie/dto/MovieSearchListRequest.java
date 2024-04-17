package com.move.move.search.searchmovie.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSearchListRequest {

    private String movieNm;

    private String directorNm;

    private String curPage;

    private String itemPerPage;

}
