package com.move.move.weeklyboxoffice.adapter;

import com.move.move.weeklyboxoffice.dto.WeeklyBoxOfficeResponseDto;

public interface WeeklyBoxOfficeAdapter {

    WeeklyBoxOfficeResponseDto getWeekBoxOffice(String date);

}
