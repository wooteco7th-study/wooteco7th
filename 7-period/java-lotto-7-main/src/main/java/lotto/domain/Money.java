package lotto.domain;

import lotto.error.ErrorMessage;
import lotto.util.NumberValidator;

public class Money {

    private static final int MIN = 1_000;
    private static final int MAX = Integer.MAX_VALUE;
    private static final int UNIT = 1_000;
    private final int value;

    public Money(final int value) {
        validate(value);
        this.value = value;
    }

    public int calculateQuotient(final int number) {
        return value / number;
    }

    public int getValue() {
        return value;
    }

    private void validate(final int value) {
        NumberValidator.validateRange(value, MIN, MAX, ErrorMessage.INVALID_EXCEEDS_MONEY_RANGE);
        NumberValidator.validateUnit(value, UNIT, ErrorMessage.INVALID_WRONG_MONEY_UNIT);
    }
}
