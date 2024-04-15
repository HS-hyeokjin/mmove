package com.move.move.weeklyboxoffice.service.impl;

import com.move.move.movieinfo.adapter.MovieDetailAdapter;
import com.move.move.utill.DateUtils;
import com.move.move.weeklyboxoffice.adapter.WeeklyBoxOfficeAdapter;
import com.move.move.weeklyboxoffice.dto.WeeklyBoxOfficeResponse;
import com.move.move.weeklyboxoffice.service.WeeklyBoxOfficeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 주간 박스 오피스 서비스
 */
@Service
public class WeeklyBoxOfficeServiceImpl implements WeeklyBoxOfficeService {

    private final WeeklyBoxOfficeAdapter weeklyBoxOfficeAdapter;
    private final MovieDetailAdapter movieDetailAdapter;


    public WeeklyBoxOfficeServiceImpl(WeeklyBoxOfficeAdapter weeklyBoxOfficeAdapter, MovieDetailAdapter movieDetailAdapter) {
        this.weeklyBoxOfficeAdapter = weeklyBoxOfficeAdapter;
        this.movieDetailAdapter = movieDetailAdapter;
    }

    /**
     * 주간 박스 오피스 데이터를 가져오는 메서드
     *
     * @param date 조회하려는 날짜를 나타내는 문자열
     * @return WeeklyBoxOfficeResponse 객체
     */
    @Cacheable(value = "weeklyBoxOfficeCache", key = "'weekly' + #nationCd + #date")
    @Override
    public WeeklyBoxOfficeResponse getWeekBoxOffice(String date) {

        DateUtils.validDate(date);

        if (date == null) {date = DateUtils.lastWeekStringDate();}

        WeeklyBoxOfficeResponse weeklyBoxOfficeData = weeklyBoxOfficeAdapter.getWeekBoxOffice(date);
        updateMoviePosters(weeklyBoxOfficeData);
        parseWeekDate(weeklyBoxOfficeData);

        return weeklyBoxOfficeData;
    }

    /**
     * 주간 박스 오피스 응답에서 영화 포스터 URL을 업데이트하는 메서드
     *
     * @param weeklyBoxOfficeData WeeklyBoxOfficeResponse 객체
     */
    private void updateMoviePosters(WeeklyBoxOfficeResponse weeklyBoxOfficeData) {
        List<WeeklyBoxOfficeResponse.WeeklyBoxOffice> weeklyBoxOfficeList = weeklyBoxOfficeData.getBoxOfficeResult().getWeeklyBoxOfficeList();
        for (WeeklyBoxOfficeResponse.WeeklyBoxOffice weeklyBoxOffice : weeklyBoxOfficeList) {
            String movieTitle = weeklyBoxOffice.getMovieNm();
            String posterUrl = movieDetailAdapter.searchMoviePoster(movieTitle);
            weeklyBoxOffice.setImageUrl(posterUrl);
        }
    }

    /**
     * 주간 박스 오피스 주간 날짜를 파싱하는 메서드
     *
     * @param weeklyBoxOfficeData WeeklyBoxOfficeResponse 객체
     */
    private void parseWeekDate(WeeklyBoxOfficeResponse weeklyBoxOfficeData) {
        String parsedWeekDate = DateUtils.parseWeekDate(weeklyBoxOfficeData.getBoxOfficeResult().getYearWeekTime());
        weeklyBoxOfficeData.getBoxOfficeResult().setYearWeekTime(parsedWeekDate);
    }
}