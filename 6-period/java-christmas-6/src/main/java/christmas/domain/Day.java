package christmas.domain;

import christmas.exception.CustomIllegalArgumentException;
import christmas.exception.ErrorMessage;

public class Day {

    private int value;

    public Day(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        if (value < 1 || value > 31) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_DAY.getMessage());
        }
    }
}
