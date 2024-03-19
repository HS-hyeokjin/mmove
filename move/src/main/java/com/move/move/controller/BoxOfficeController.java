package com.move.move.controller;

import com.move.move.dto.DailyBoxOfficeResponseDto;
import com.move.move.dto.WeeklyBoxOfficeResponseDto;
import com.move.move.exception.InvalidDateException;
import com.move.move.service.DailyBoxOfficeService;
import com.move.move.service.WeeklyBoxOfficeService;
import com.move.move.utill.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

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
        if (date != null) {
            LocalDate parsedDate = DateUtils.parseDate(date);
            if (parsedDate.isAfter(LocalDate.now())) {
                throw new InvalidDateException("전일 이후는 불가능 합니다.");
            }
        }
        model.addAttribute("dailyBoxOfficeData", dailyBoxOfficeService.getDailyBoxOffice("A",date));
        model.addAttribute("koDailyBoxOfficeData", dailyBoxOfficeService.getDailyBoxOffice("K",date));
        model.addAttribute("foDailyBoxOfficeData", dailyBoxOfficeService.getDailyBoxOffice("F",date));
        return "box-office/daily-box-office";
    }

    @GetMapping("/weekly-box-office")
    public String getWeeklyBoxOffice(Model model, @RequestParam(required = false) String date) {
        WeeklyBoxOfficeResponseDto weeklyBoxOfficeData = weeklyBoxOfficeService.getWeekBoxOffice(date);
        model.addAttribute("weeklyBoxOfficeData", weeklyBoxOfficeData);
        return "box-office/weekly-box-office";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDateException.class)
    public String handleInvalidDateException(Model model, InvalidDateException ex) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}