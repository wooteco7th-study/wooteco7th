package christmas.domain;

import christmas.exception.CustomIllegalArgumentException;
import christmas.exception.ErrorMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Day {

    private final int value;

    public Day(final int value) {
        validate(value);
        this.value = value;
    }

    public boolean isWeekend() {
        LocalDate now = LocalDate.of(2023, 12, value);
        return now.getDayOfWeek().equals(DayOfWeek.FRIDAY) || now.getDayOfWeek().equals(DayOfWeek.SATURDAY);
    }

    public boolean isSpecialDay() {
        LocalDate now = LocalDate.of(2023, 12, value);
        return now.getDayOfWeek().equals(DayOfWeek.SUNDAY) || value == 25;
    }

    private void validate(final int value) {
        if (value < 1 || value > 31) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_DAY.getMessage());
        }
    }

    public int diffFromFirstDay() {
        return value - 1;
    }

    public Day add(Day day) {
        return new Day(value + day.value);
    }

    public Day subtract(Day day) {
        return new Day(value - day.value);
    }

    public int getValue() {
        return value;
    }
}
