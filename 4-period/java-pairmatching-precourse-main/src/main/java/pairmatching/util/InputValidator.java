package pairmatching.util;

import java.util.regex.Pattern;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public class InputValidator {

    private InputValidator() {
    }

    public static void validateNotNullOrBlank(final String input, final ErrorMessage message) {
        if (input == null || input.isBlank()) {
            throw new CustomIllegalArgumentException(message);
        }
    }

    public static boolean isInvalidPattern(String input, Pattern pattern) {
        return !pattern.matcher(input).matches();
    }
}
