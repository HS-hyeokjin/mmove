package com.move.move.service.impl;

import com.move.move.adapter.MovieDetailAdapter;
import com.move.move.adapter.WeeklyBoxOfficeAdapter;
import com.move.move.dto.WeeklyBoxOfficeResponseDto;
import com.move.move.exception.DateCalculationException;
import com.move.move.service.WeeklyBoxOfficeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class WeeklyBoxOfficeServiceImpl implements WeeklyBoxOfficeService {

    private final WeeklyBoxOfficeAdapter weeklyBoxOfficeAdapter;
    private final MovieDetailAdapter movieDetailAdapter;


    public WeeklyBoxOfficeServiceImpl(WeeklyBoxOfficeAdapter weeklyBoxOfficeAdapter, MovieDetailAdapter movieDetailAdapter) {
        this.weeklyBoxOfficeAdapter = weeklyBoxOfficeAdapter;
        this.movieDetailAdapter = movieDetailAdapter;
    }

    @Cacheable(value = "weeklyBoxOfficeCache", key = "'weekly' + #nationCd + #date")
    @Override
    public WeeklyBoxOfficeResponseDto getWeekBoxOffice(String date) {
        if(date == null){
            date = before7days();
        }

        WeeklyBoxOfficeResponseDto weeklyBoxOfficeData = weeklyBoxOfficeAdapter.getWeekBoxOffice(date);

        List<WeeklyBoxOfficeResponseDto.WeeklyBoxOffice> weeklyBoxOfficeList = weeklyBoxOfficeData.getBoxOfficeResult().getWeeklyBoxOfficeList();
        for (WeeklyBoxOfficeResponseDto.WeeklyBoxOffice weeklyBoxOffice : weeklyBoxOfficeList) {
            String movieTitle = weeklyBoxOffice.getMovieNm();

            String posterUrl = movieDetailAdapter.searchMoviePoster(movieTitle);
            weeklyBoxOffice.setImageUrl(posterUrl);
        }

        String parsedWeekDate= parseWeekData(weeklyBoxOfficeData.getBoxOfficeResult().getYearWeekTime());
        weeklyBoxOfficeData.getBoxOfficeResult().setYearWeekTime(parsedWeekDate);

        return weeklyBoxOfficeData;
    }

    private String before7days() {
        try {
            LocalDate date = LocalDate.now().minusDays(7);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            return date.format(formatter);
        } catch (DateTimeException e) {
            log.error("before7days() 메서드 오류", e);
            throw new DateCalculationException("7일 전 데이터 오류", e);
        }
    }

    private String parseWeekData(String weekData) {
        try {
            String yearStr = weekData.substring(0, 4);
            String weekNumberStr = weekData.substring(4);

            int year = Integer.parseInt(yearStr);
            int weekNumber = Integer.parseInt(weekNumberStr);

            LocalDate januaryFirst = LocalDate.of(year, 1, 1);
            LocalDate targetDate = januaryFirst.plusWeeks(weekNumber - 1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

            return targetDate.format(formatter);
        } catch (DateTimeException | NumberFormatException | StringIndexOutOfBoundsException | NullPointerException e) {
            log.error("parseWeekData() 메서드 오류", e);
            throw new DateCalculationException("주간 date 파싱 실패", e);
        }
    }

}
