package com.move.move.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDetailsResponseDto {

    private List<MovieDetailResultDto> results;

}

