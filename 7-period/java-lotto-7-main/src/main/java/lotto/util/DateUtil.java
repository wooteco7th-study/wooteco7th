package lotto.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {
    private DateUtil() {
    }

    // 두 날짜 사이의 일 수 계산
    public static long daysBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    // 날짜에 일 수 더하기
    public static LocalDate addDays(LocalDate date, long days) {
        return date.plusDays(days);
    }

    // 날짜에 일 수 빼기
    public static LocalDate subtractDays(LocalDate date, long days) {
        return date.minusDays(days);
    }

    // 날짜가 범위 내에 있는지 확인 (포함)
    public static boolean isBetweenInclusive(LocalDate target, LocalDate start, LocalDate end) {
        return (target.isEqual(start) || target.isAfter(start)) && (target.isEqual(end) || target.isBefore(end));
    }

    // 주말 여부 확인
    public static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    // 평일 여부 확인
    public static boolean isWeekday(LocalDate date) {
        return !isWeekend(date);
    }

    // 윤년 여부 확인
    public static boolean isLeapYear(LocalDate date) {
        return date.isLeapYear();
    }


    // 문자열을 날짜로 파싱
    public static LocalDate parse(String dateString, String pattern) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }


    // 두 날짜 사이의 월 수 계산
    public static long monthsBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.MONTHS.between(start, end);
    }

    // 두 날짜 사이의 연도 수 계산
    public static long yearsBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.YEARS.between(start, end);
    }

    // 날짜 비교 (이전인지 확인)
    public static boolean isBefore(LocalDate date1, LocalDate date2) {
        return date1.isBefore(date2);
    }

    // 날짜 비교 (이후인지 확인)
    public static boolean isAfter(LocalDate date1, LocalDate date2) {
        return date1.isAfter(date2);
    }

    // 날짜 비교 (동일한 날짜인지 확인)
    public static boolean isEqual(LocalDate date1, LocalDate date2) {
        return date1.isEqual(date2);
    }

    // 두 날짜 사이의 모든 날짜 리스트 반환
    public static List<LocalDate> getDatesBetween(LocalDate start, LocalDate end) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = start;
        while (!current.isAfter(end)) {
            dates.add(current);
            current = current.plusDays(1);
        }
        return dates;
    }

}
