package com.move.move.dailyboxoffice.service;

import com.move.move.dailyboxoffice.dto.DailyBoxOfficeResponseDto;

public interface DailyBoxOfficeService {

    DailyBoxOfficeResponseDto getDailyBoxOffice(String nationCd, String date);
}
