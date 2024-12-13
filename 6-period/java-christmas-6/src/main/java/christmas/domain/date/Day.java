package christmas.domain.date;

import christmas.exception.CustomIllegalArgumentException;
import christmas.exception.ErrorMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Day {

    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int CHRISTMAS = 25;

    private final LocalDate today;

    public Day(final int day) {
        validate(day);
        this.today = LocalDate.of(YEAR, MONTH, day);
    }

    public boolean isInDecember() {
        return today.getMonth().equals(Month.DECEMBER);
    }

    public boolean isSpecialDay() {
        return isInDecember()
                && (today.getDayOfWeek() == DayOfWeek.SUNDAY || today.getDayOfMonth() == CHRISTMAS);
    }

    public boolean isWeekend() {
        return today.getDayOfWeek() == DayOfWeek.FRIDAY || today.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public int calculateIntervalFromBeginning() {
        return today.getDayOfMonth() - 1;
    }

    private void validate(final int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_DATE);
        }
    }

    public int getToday() {
        return today.getDayOfMonth();
    }

    public boolean isWithinChristmas() {
        LocalDate christmas = LocalDate.of(YEAR, MONTH, CHRISTMAS);
        return today.isEqual(christmas) || today.isBefore(christmas);
    }
}
