package baseball.util;

import baseball.exception.ExceptionMessage;

public class InputValidator {

    public static void validateNotNullOrBlank(final String input) {
        if (input == null) {
            throw new IllegalArgumentException(ExceptionMessage.NULL_INPUT.getMessage());
        }
        if (input.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.BLANK_INPUT.getMessage());
        }
    }
}
