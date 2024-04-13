package com.move.move.dailyboxoffice.adapter;

import com.move.move.dailyboxoffice.dto.DailyBoxOfficeRequestDto;
import com.move.move.dailyboxoffice.dto.DailyBoxOfficeResponseDto;

public interface DailyBoxOfficeAdapter {

    DailyBoxOfficeResponseDto getDailyBoxOfficeData(DailyBoxOfficeRequestDto dailyBoxOfficeRequestDto);

}
