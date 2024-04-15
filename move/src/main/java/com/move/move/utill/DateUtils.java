package com.move.move.utill;

import com.move.move.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateUtils {

    public static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(dateStr, formatter);
    }

    public static String yesterdayStringDate() {
        LocalDate date = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = date.format(formatter);
        return Stream.of(formattedDate)
                .collect(Collectors.joining());
    }

    public static void validDate(String date){
        if (date != null && !isBeforeToday(date)) {
            throw new InvalidDateException("에러 : 해당 날짜 데이터가 존재하지 않습니다.");
        }
    }

    private static boolean isBeforeToday(String dateStr) {
        LocalDate parsedDate = parseDate(dateStr);
        return parsedDate.isBefore(LocalDate.now());
    }
}