package com.move.move.utill;

import com.move.move.exception.DateCalculationException;
import com.move.move.exception.InvalidDateException;

import java.time.DateTimeException;
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

    public static String lastWeekStringDate(){
        try {
            LocalDate date = LocalDate.now().minusDays(7);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            return date.format(formatter);
        } catch (DateTimeException e) {
            throw new DateCalculationException("7일 전 데이터 오류", e);
        }
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

    public static String parseWeekDate(String weekData) {
        try {
            String yearStr = weekData.substring(0, 4);
            String weekNumberStr = weekData.substring(4);

            int year = Integer.parseInt(yearStr);
            int weekNumber = Integer.parseInt(weekNumberStr);

            LocalDate januaryFirst = LocalDate.of(year, 1, 1);
            LocalDate targetDate = januaryFirst.plusWeeks(weekNumber - 1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

            return targetDate.format(formatter);
        } catch (DateTimeException | NumberFormatException | StringIndexOutOfBoundsException | NullPointerException e) {
            throw new DateCalculationException("주간 date 파싱 실패", e);
        }
    }
}