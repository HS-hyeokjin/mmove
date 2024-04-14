package com.move.move.dailyboxoffice.service;

import com.move.move.dailyboxoffice.dto.DailyBoxOfficeResponse;

public interface DailyBoxOfficeService {

    DailyBoxOfficeResponse getDailyBoxOffice(String nationCd, String date);
}
