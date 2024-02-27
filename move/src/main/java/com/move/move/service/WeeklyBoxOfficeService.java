package com.move.move.service;

import com.move.move.dto.WeeklyBoxOfficeResponseDto;

public interface WeeklyBoxOfficeService {

    WeeklyBoxOfficeResponseDto getWeekBoxOffice(String date);

}
