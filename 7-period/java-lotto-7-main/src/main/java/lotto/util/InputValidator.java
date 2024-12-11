package lotto.util;

import lotto.exception.CustomIllegalArgumentException;
import lotto.exception.ErrorMessage;

public class InputValidator {
    private InputValidator() {
    }

    public static void validateNotNullOrBlank(final String input, final ErrorMessage message) {
        if (input == null || input.isBlank()) {
            throw new CustomIllegalArgumentException(message);
        }
    }
}
