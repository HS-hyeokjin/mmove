package com.move.move.controller;

import com.move.move.dto.DailyBoxOfficeResponseDto;
import com.move.move.dto.WeeklyBoxOfficeResponseDto;
import com.move.move.service.DailyBoxOfficeService;
import com.move.move.service.WeeklyBoxOfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoxOfficeController {

    private final DailyBoxOfficeService dailyBoxOfficeService;
    private final WeeklyBoxOfficeService weeklyBoxOfficeService;

    public BoxOfficeController(DailyBoxOfficeService dailyBoxOfficeService, WeeklyBoxOfficeService weeklyBoxOfficeService) {
        this.dailyBoxOfficeService = dailyBoxOfficeService;
        this.weeklyBoxOfficeService = weeklyBoxOfficeService;
    }

    @GetMapping("/daily-box-office")
    public String getDailyBoxOffice(Model model) {
        DailyBoxOfficeResponseDto dailyBoxOfficeData = dailyBoxOfficeService.getDailyBoxOffice(null);
        DailyBoxOfficeResponseDto koDailyBoxOfficeData = dailyBoxOfficeService.getDailyBoxOffice("K");
        DailyBoxOfficeResponseDto foDailyBoxOfficeData = dailyBoxOfficeService.getDailyBoxOffice("F");

        model.addAttribute("dailyBoxOfficeData", dailyBoxOfficeData);
        model.addAttribute("koDailyBoxOfficeData", koDailyBoxOfficeData);
        model.addAttribute("foDailyBoxOfficeData", foDailyBoxOfficeData);
        return "daily-box-office";
    }

    @GetMapping("/weekly-box-office")
    public String getWeeklyBoxOffice(Model model) {
        WeeklyBoxOfficeResponseDto weeklyBoxOfficeData = weeklyBoxOfficeService.getWeekBoxOffice("20240212");
        model.addAttribute("weeklyBoxOfficeData", weeklyBoxOfficeData);
        return "weekly-box-office";
    }
}

