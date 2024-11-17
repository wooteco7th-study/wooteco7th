package lotto.domain;

import lotto.error.ErrorMessage;
import lotto.util.NumberValidator;

public class BonusNumber {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private final int value;

    public BonusNumber(final int value) {
        validate(value);
        this.value = value;
    }

    public boolean isMatchedNumber(final int number) {
        return value == number;
    }

    public int getValue() {
        return value;
    }

    private void validate(final int value) {
        NumberValidator.validateRange(value, MIN_NUMBER, MAX_NUMBER, ErrorMessage.INVALID_EXCEEDS_LOTTO_NUMBER_RANGE);
    }
}
