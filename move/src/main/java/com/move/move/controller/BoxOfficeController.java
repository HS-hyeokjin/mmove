package com.move.move.controller;

import com.move.move.dto.DailyBoxOfficeResponseDto;
import com.move.move.dto.WeeklyBoxOfficeResponseDto;
import com.move.move.service.DailyBoxOfficeService;
import com.move.move.service.WeeklyBoxOfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoxOfficeController {

    private final DailyBoxOfficeService dailyBoxOfficeService;
    private final WeeklyBoxOfficeService weeklyBoxOfficeService;

    public BoxOfficeController(DailyBoxOfficeService dailyBoxOfficeService, WeeklyBoxOfficeService weeklyBoxOfficeService) {
        this.dailyBoxOfficeService = dailyBoxOfficeService;
        this.weeklyBoxOfficeService = weeklyBoxOfficeService;
    }

    @GetMapping("/daily-box-office")
    public String getDailyBoxOffice(Model model, @RequestParam(required = false) String date) {

        DailyBoxOfficeResponseDto dailyBoxOfficeData = dailyBoxOfficeService.getDailyBoxOffice(null,date);
        DailyBoxOfficeResponseDto koDailyBoxOfficeData = dailyBoxOfficeService.getDailyBoxOffice("K",date);
        DailyBoxOfficeResponseDto foDailyBoxOfficeData = dailyBoxOfficeService.getDailyBoxOffice("F",date);

        model.addAttribute("dailyBoxOfficeData", dailyBoxOfficeData);
        model.addAttribute("koDailyBoxOfficeData", koDailyBoxOfficeData);
        model.addAttribute("foDailyBoxOfficeData", foDailyBoxOfficeData);
        return "box-office/daily-box-office";
    }

    @GetMapping("/weekly-box-office")
    public String getWeeklyBoxOffice(Model model,@RequestParam(required = false) String date) {
        WeeklyBoxOfficeResponseDto weeklyBoxOfficeData = weeklyBoxOfficeService.getWeekBoxOffice(date);
        model.addAttribute("weeklyBoxOfficeData", weeklyBoxOfficeData);
        return "box-office/weekly-box-office";
    }
}

