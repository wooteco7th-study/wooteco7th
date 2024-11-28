package vendingmachine.util;

import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.ErrorMessage;

public class InputValidator {
    public static void validateNotNullOrBlank(final String input, final ErrorMessage message) {
        if (input == null || input.isBlank()) {
            throw new CustomIllegalArgumentException(message);
        }
    }
}
