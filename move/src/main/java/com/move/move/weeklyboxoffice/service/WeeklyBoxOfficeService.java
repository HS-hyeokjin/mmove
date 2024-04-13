package com.move.move.weeklyboxoffice.service;

import com.move.move.weeklyboxoffice.dto.WeeklyBoxOfficeResponseDto;

public interface WeeklyBoxOfficeService {

    WeeklyBoxOfficeResponseDto getWeekBoxOffice(String date);

}
