package racingcar.domain;

import racingcar.exception.ErrorMessage;
import racingcar.util.NumberValidator;

public class Attempt {

    private static final int MIN = 1;
    private static final int MAX = 100;

    private int value;

    public Attempt(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        NumberValidator.validateRange(value, MIN, MAX, ErrorMessage.INVALID_INPUT);
    }

    public void decreaseAttempt() {
        this.value--;
    }

    public boolean isZero() {
        return this.value == 0;
    }
}
