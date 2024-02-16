package com.move.move.controller;

import com.move.move.dto.DailyBoxOfficeResponseDto;
import com.move.move.service.DailyBoxOfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoxOfficeController {

    private final DailyBoxOfficeService dailyBoxOfficeService;

    public BoxOfficeController(DailyBoxOfficeService dailyBoxOfficeService) {
        this.dailyBoxOfficeService = dailyBoxOfficeService;
    }

    @GetMapping("/daily-box-office")
    public String getDailyBoxOffice(Model model) {
        DailyBoxOfficeResponseDto dailyBoxOffice = dailyBoxOfficeService.getDailyBoxOffice();
        dailyBoxOffice.printDetails();

        model.addAttribute("dailyBoxOfficeData", dailyBoxOffice);
        return "daily-box-office";
    }
}

