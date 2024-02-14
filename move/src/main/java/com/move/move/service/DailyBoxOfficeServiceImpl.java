package com.move.move.service;

import com.move.move.adapter.DailyBoxOfficeAdapter;
import com.move.move.dto.DailyBoxOfficeRequestDto;
import com.move.move.dto.DailyBoxOfficeResponseDto;
import org.springframework.stereotype.Service;

@Service
public class DailyBoxOfficeServiceImpl implements DailyBoxOfficeService{

    private final DailyBoxOfficeAdapter dailyBoxOfficeAdapter;

    public DailyBoxOfficeServiceImpl(DailyBoxOfficeAdapter dailyBoxOfficeAdapter) {
        this.dailyBoxOfficeAdapter = dailyBoxOfficeAdapter;
    }

    public DailyBoxOfficeResponseDto getDailyBoxOffice(){

        DailyBoxOfficeRequestDto dailyBoxOfficeRequestDto = new DailyBoxOfficeRequestDto();
        dailyBoxOfficeRequestDto.setTargetDt("20240201");

        DailyBoxOfficeResponseDto dailyBoxOfficeData = dailyBoxOfficeAdapter.getDailyBoxOfficeData(dailyBoxOfficeRequestDto);

        return dailyBoxOfficeData;
    }


}
