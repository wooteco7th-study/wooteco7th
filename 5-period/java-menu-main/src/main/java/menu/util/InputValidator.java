package menu.util;

import java.util.regex.Pattern;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;

public class InputValidator {

    private InputValidator() {
    }

    public static void validateNotNull(final String input, final ErrorMessage message) {
        if (input == null) {
            throw new CustomIllegalArgumentException(message);
        }
    }

    public static boolean isInvalidPattern(String input, Pattern pattern) {
        return !pattern.matcher(input).matches();
    }
}
