package vendingmachine.domain;

import vendingmachine.error.ErrorMessage;
import vendingmachine.util.NumberValidator;

public class Money {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private int value;

    public Money(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        NumberValidator.validateRange(value, MIN_VALUE, MAX_VALUE, ErrorMessage.INVALID_EXCEEDS_MONEY_RANGE);
    }

    public boolean isExceedsValue(final int value) {
        return this.value >= value;
    }

    public void subtractValue(final int value) {
        this.value -= value;
    }

    public int getValue() {
        return value;
    }
}
