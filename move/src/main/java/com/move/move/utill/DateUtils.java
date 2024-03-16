package com.move.move.utill;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(dateStr, formatter);
    }
}