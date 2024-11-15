package racingcar.util;

import racingcar.constant.ErrorMessage;

public class StringValidator {

    public static void validateLength(final String value, final int maxLength, final ErrorMessage errorMessage) {
        if (value.length() > maxLength) {
            throw new IllegalArgumentException(errorMessage.getMessage());
        }
    }

    public static void validateFormat(final String value, final ErrorMessage errorMessage) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(errorMessage.getMessage());
        }
    }

    private StringValidator() {

    }
}
