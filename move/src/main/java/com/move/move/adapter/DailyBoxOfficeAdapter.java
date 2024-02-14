package com.move.move.adapter;

import com.move.move.dto.DailyBoxOfficeRequestDto;
import com.move.move.dto.DailyBoxOfficeResponseDto;

public interface DailyBoxOfficeAdapter {

    DailyBoxOfficeResponseDto getDailyBoxOfficeData(DailyBoxOfficeRequestDto dailyBoxOfficeRequestDto);

}
