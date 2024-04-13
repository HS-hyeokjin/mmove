package com.move.move.weeklyboxoffice.controller;

import com.move.move.weeklyboxoffice.dto.WeeklyBoxOfficeResponseDto;
import com.move.move.weeklyboxoffice.service.WeeklyBoxOfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeeklyBoxOfficeController {

    private final WeeklyBoxOfficeService weeklyBoxOfficeService;

    public WeeklyBoxOfficeController(WeeklyBoxOfficeService weeklyBoxOfficeService) {
        this.weeklyBoxOfficeService = weeklyBoxOfficeService;
    }

    @GetMapping("/weekly-box-office")
    public String getWeeklyBoxOffice(Model model, @RequestParam(required = false) String date) {
        WeeklyBoxOfficeResponseDto weeklyBoxOfficeData = weeklyBoxOfficeService.getWeekBoxOffice(date);
        model.addAttribute("weeklyBoxOfficeData", weeklyBoxOfficeData);
        return "box-office/weekly-box-office";
    }
}
