package christmas.domain;

import static christmas.exception.ExceptionMessage.INVALID_NUMBER;
import static christmas.exception.ExceptionMessage.INVALID_ORDER;

public class Quantity {

    private static final int MIN_QUANTITY = 1;

    private final int value;

    public Quantity(final String input) {
        validateNumber(input);
        int value = Integer.parseInt(input);
        validateRange(value);
        this.value = value;
    }

    private void validateNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER.getMessage());
        }
    }

    private void validateRange(final int value) {
        if (value < MIN_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public int getValue() {
        return value;
    }
}
