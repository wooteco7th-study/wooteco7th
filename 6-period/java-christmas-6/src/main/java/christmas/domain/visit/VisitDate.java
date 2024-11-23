package christmas.domain.visit;

import christmas.error.ErrorMessage;
import christmas.util.LocalDateConvertor;
import christmas.util.NumberValidator;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class VisitDate {

    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final LocalDate CHRISTMAS = LocalDateConvertor.convert(YEAR, MONTH, 25);
    private static final List<LocalDate> SPECIAL_DAYS = LocalDateConvertor.convert(YEAR, MONTH, 3, 10, 17, 24, 25, 31);

    private final LocalDate localDate;

    private VisitDate(final LocalDate localDate) {
        this.localDate = localDate;
    }

    public static VisitDate of(final int day) {
        validate(day);
        return new VisitDate(LocalDateConvertor.convert(YEAR, MONTH, day));
    }

    public int getDayOfMonth() {
        return localDate.getDayOfMonth();
    }

    public boolean isWeekday() {
        final DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY
                || dayOfWeek == DayOfWeek.MONDAY
                || dayOfWeek == DayOfWeek.TUESDAY
                || dayOfWeek == DayOfWeek.WEDNESDAY
                || dayOfWeek == DayOfWeek.THURSDAY;
    }

    public boolean isWeekend() {
        final DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY
                || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isBeforeChristmas() {
        return localDate.isBefore(CHRISTMAS);
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAYS.contains(localDate);
    }


    private static void validate(final int day) {
        NumberValidator.validateRange(day, MIN_DAY, MAX_DAY, ErrorMessage.INVALID_VISIT_DAY_FORMAT);
    }
}
