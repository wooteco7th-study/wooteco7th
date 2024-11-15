package racingcar.domain;

import racingcar.constant.ErrorMessage;
import racingcar.util.NumberValidator;

public class Attempt {

    private final int MIN_ATTEMPT = 1;
    private final int MAX_ATTEMPT = 10_000;
    private final int value;

    public Attempt(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        NumberValidator.validateRange(value, MIN_ATTEMPT, MAX_ATTEMPT, ErrorMessage.INVALID_EXCEEDS_ATTEMPT_RANGE);
    }

    public int getValue() {
        return value;
    }
}
