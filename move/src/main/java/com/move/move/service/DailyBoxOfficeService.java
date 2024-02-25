package com.move.move.service;

import com.move.move.dto.DailyBoxOfficeResponseDto;

public interface DailyBoxOfficeService {

    DailyBoxOfficeResponseDto getDailyBoxOffice(String nationCd, String date);
}
