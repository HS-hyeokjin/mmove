package com.move.move.service.impl;

import com.move.move.adapter.DailyBoxOfficeAdapter;
import com.move.move.adapter.MovieDetailAdapter;
import com.move.move.dto.DailyBoxOfficeRequestDto;
import com.move.move.dto.DailyBoxOfficeResponseDto;
import com.move.move.service.DailyBoxOfficeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DailyBoxOfficeServiceImpl implements DailyBoxOfficeService {

    private final DailyBoxOfficeAdapter dailyBoxOfficeAdapter;
    private final MovieDetailAdapter movieDetailAdapter;

    public DailyBoxOfficeServiceImpl(DailyBoxOfficeAdapter dailyBoxOfficeAdapter, MovieDetailAdapter movieDetailAdapter) {
        this.dailyBoxOfficeAdapter = dailyBoxOfficeAdapter;
        this.movieDetailAdapter = movieDetailAdapter;
    }

    @Override
    public DailyBoxOfficeResponseDto getDailyBoxOffice(String nationCd,String date){

        if(date == null) {
            date = yesterdayStringDate();
        }
        DailyBoxOfficeRequestDto dailyBoxOfficeRequestDto = new DailyBoxOfficeRequestDto();
        dailyBoxOfficeRequestDto.setTargetDt(date);
        dailyBoxOfficeRequestDto.setRepNationCd(nationCd);
        DailyBoxOfficeResponseDto dailyBoxOfficeData = dailyBoxOfficeAdapter.getDailyBoxOfficeData(dailyBoxOfficeRequestDto);

        List<DailyBoxOfficeResponseDto.DailyBoxOffice> dailyBoxOfficeList = dailyBoxOfficeData.getBoxOfficeResult().getDailyBoxOfficeList();
        for (DailyBoxOfficeResponseDto.DailyBoxOffice dailyBoxOffice : dailyBoxOfficeList) {
            String movieTitle = dailyBoxOffice.getMovieNm();

            String posterUrl = movieDetailAdapter.searchMoviePoster(movieTitle);
            dailyBoxOffice.setImageUrl(posterUrl);
        }
        return dailyBoxOfficeData;
    }


    private String yesterdayStringDate(){
        LocalDate date = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = date.format(formatter);
        return Stream.of(formattedDate)
                .collect(Collectors.joining());
    }

}
