package com.move.move.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "에러 : " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(ApiRequestException.class)
    public String handleApiRequestException(ApiRequestException ex, Model model) {
        model.addAttribute("errorMessage", "에러 : " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(DateCalculationException.class)
    public String handleDateCalculationException(DateCalculationException ex, Model model) {
        model.addAttribute("errorMessage", "에러 : " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(DuplicateIdException.class)
    public String handleDuplicateIdException(DuplicateIdException  ex, Model model){
        model.addAttribute("errorMessage", "에러 : " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(InvalidDateException.class)
    public String handleInvalidDateException(InvalidDateException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}
