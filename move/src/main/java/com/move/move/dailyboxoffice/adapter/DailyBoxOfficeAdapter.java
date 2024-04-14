package com.move.move.dailyboxoffice.adapter;

import com.move.move.dailyboxoffice.dto.DailyBoxOfficeRequest;
import com.move.move.dailyboxoffice.dto.DailyBoxOfficeResponse;

public interface DailyBoxOfficeAdapter {

    DailyBoxOfficeResponse getDailyBoxOfficeData(DailyBoxOfficeRequest dailyBoxOfficeRequest);

}
