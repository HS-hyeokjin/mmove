package com.move.move.dailyboxoffice.service.impl;

import com.move.move.dailyboxoffice.adapter.DailyBoxOfficeAdapter;
import com.move.move.movieinfo.adapter.MovieDetailAdapter;
import com.move.move.dailyboxoffice.dto.DailyBoxOfficeRequest;
import com.move.move.dailyboxoffice.dto.DailyBoxOfficeResponse;
import com.move.move.dailyboxoffice.service.DailyBoxOfficeService;
import com.move.move.utill.DateUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyBoxOfficeServiceImpl implements DailyBoxOfficeService {

    private final DailyBoxOfficeAdapter dailyBoxOfficeAdapter;
    private final MovieDetailAdapter movieDetailAdapter;

    public DailyBoxOfficeServiceImpl(DailyBoxOfficeAdapter dailyBoxOfficeAdapter, MovieDetailAdapter movieDetailAdapter) {
        this.dailyBoxOfficeAdapter = dailyBoxOfficeAdapter;
        this.movieDetailAdapter = movieDetailAdapter;
    }

    @Cacheable(value = "dailyBoxOfficeCache", key = "'daily' + #nationCd + #date")
    @Override
    public DailyBoxOfficeResponse getDailyBoxOffice(String nationCd, String date) {

        DateUtils.validDate(date);

        if (date == null) {
            date = DateUtils.yesterdayStringDate();
        }

        DailyBoxOfficeRequest dailyBoxOfficeRequest = new DailyBoxOfficeRequest();
        dailyBoxOfficeRequest.setTargetDt(date);

        if (!nationCd.equals("A")) {
            dailyBoxOfficeRequest.setRepNationCd(nationCd);
        }

        DailyBoxOfficeResponse dailyBoxOfficeData = dailyBoxOfficeAdapter.getDailyBoxOfficeData(dailyBoxOfficeRequest);
        updateMoviePosters(dailyBoxOfficeData);

        return dailyBoxOfficeData;
    }

    private void updateMoviePosters(DailyBoxOfficeResponse dailyBoxOfficeData) {
        List<DailyBoxOfficeResponse.DailyBoxOffice> dailyBoxOfficeList = dailyBoxOfficeData.getBoxOfficeResult().getDailyBoxOfficeList();
        for (DailyBoxOfficeResponse.DailyBoxOffice dailyBoxOffice : dailyBoxOfficeList) {
            String movieTitle = dailyBoxOffice.getMovieNm();
            String posterUrl = movieDetailAdapter.searchMoviePoster(movieTitle);
            dailyBoxOffice.setImageUrl(posterUrl);
        }
    }
}

