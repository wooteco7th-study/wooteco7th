package christmas.domain;

import christmas.exception.CustomIllegalArgumentException;
import christmas.exception.ErrorMessage;

public class Day {

    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private final int day;

    public Day(final int day) {
        validate(day);
        this.day = day;
    }

    private void validate(final int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_DATE);
        }
    }
}
