package christmas.util;

import christmas.exception.ErrorMessage;
import java.util.List;

public class InputValidator {

    public static void validateNotNullOrBlank(final String input) {
        if (input == null) {
            throw new IllegalArgumentException(ErrorMessage.NULL.getMessage());
        }
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.BLANK.getMessage());
        }
    }
}
