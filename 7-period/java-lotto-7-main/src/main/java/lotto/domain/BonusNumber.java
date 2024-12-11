package lotto.domain;

import lotto.error.ErrorMessage;
import lotto.util.NumberValidator;

public class BonusNumber {

    private final int value;

    public BonusNumber(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        NumberValidator.validateRange(value, Lotto.NUMBER_MIN, Lotto.NUMBER_MAX, ErrorMessage.EXCEEDS_NUMBER);
    }

    public boolean isMatchedNumber(final int number) {
        return value == number;
    }

    public int getValue() {
        return value;
    }
}
