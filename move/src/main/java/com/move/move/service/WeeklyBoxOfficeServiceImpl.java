package com.move.move.service;

import com.move.move.adapter.MovieDetailAdapter;
import com.move.move.adapter.WeeklyBoxOfficeAdapter;
import com.move.move.dto.WeeklyBoxOfficeResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WeeklyBoxOfficeServiceImpl implements WeeklyBoxOfficeService {

    private final WeeklyBoxOfficeAdapter weeklyBoxOfficeAdapter;
    private final MovieDetailAdapter movieDetailAdapter;


    public WeeklyBoxOfficeServiceImpl(WeeklyBoxOfficeAdapter weeklyBoxOfficeAdapter, MovieDetailAdapter movieDetailAdapter) {
        this.weeklyBoxOfficeAdapter = weeklyBoxOfficeAdapter;
        this.movieDetailAdapter = movieDetailAdapter;
    }

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

    private String before7days(){
        LocalDate date = LocalDate.now().minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = date.format(formatter);
        return Stream.of(formattedDate)
                .collect(Collectors.joining());
    }

    private String parseWeekData(String weekData) {
        String yearStr = weekData.substring(0, 4);
        String weekNumberStr = weekData.substring(4);

        int year = Integer.parseInt(yearStr);
        int weekNumber = Integer.parseInt(weekNumberStr);

        LocalDate januaryFirst = LocalDate.of(year, 1, 1);
        LocalDate targetDate = januaryFirst.plusWeeks(weekNumber - 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        return targetDate.format(formatter);
    }

}
