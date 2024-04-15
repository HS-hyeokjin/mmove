package com.move.move.dailyboxoffice.controller;

import com.move.move.dailyboxoffice.service.DailyBoxOfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DailyBoxOfficeController {

    private final DailyBoxOfficeService dailyBoxOfficeService;

    public DailyBoxOfficeController(DailyBoxOfficeService dailyBoxOfficeService) {
        this.dailyBoxOfficeService = dailyBoxOfficeService;
    }

    @GetMapping("/daily-box-office")
    public String getDailyBoxOffice(Model model, @RequestParam(required = false) String date) {
        model.addAttribute("dailyBoxOfficeData", dailyBoxOfficeService.getDailyBoxOffice("A", date));
        model.addAttribute("koDailyBoxOfficeData", dailyBoxOfficeService.getDailyBoxOffice("K", date));
        model.addAttribute("foDailyBoxOfficeData", dailyBoxOfficeService.getDailyBoxOffice("F", date));
        return "box-office/daily-box-office";
    }

}