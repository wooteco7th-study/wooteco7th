package racingcar.domain;

import racingcar.constant.ErrorMessage;
import racingcar.util.StringValidator;

public class CarName {

    private static final int MAX_LENGTH = 5;
    private final String value;

    public CarName(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        StringValidator.validateFormat(value, ErrorMessage.INVALID_WRONG_NAME_FORMAT);
        StringValidator.validateLength(value, MAX_LENGTH, ErrorMessage.INVALID_EXCEEDS_CAR_NAME_LENGTH);
    }

    public String getValue() {
        return value;
    }
}
