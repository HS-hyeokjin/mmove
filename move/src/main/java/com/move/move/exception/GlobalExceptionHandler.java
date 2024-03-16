package com.move.move.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, Model model) {
        log.error("예기치 못한 에러 발생:", ex);
        model.addAttribute("errorMessage", "에러 : " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(ApiRequestException.class)
    public String handleApiRequestException(ApiRequestException ex, Model model) {
        log.error("API 요청 에러:", ex);
        model.addAttribute("errorMessage", "에러 : " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(DateCalculationException.class)
    public String handleDateCalculationException(DateCalculationException ex, Model model) {
        log.error("Date calculation error:", ex);
        model.addAttribute("errorMessage", "에러 : " + ex.getMessage());
        return "error";
    }
}
