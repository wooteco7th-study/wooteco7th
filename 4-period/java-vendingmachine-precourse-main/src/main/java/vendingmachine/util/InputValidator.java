package vendingmachine.util;

import java.util.List;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.ErrorMessage;

public class InputValidator {
    public static void validateNotNullOrBlank(final String input, final ErrorMessage message) {
        if (input == null) {
            throw new CustomIllegalArgumentException(message);
        }
        if (input.isBlank()) {
            throw new CustomIllegalArgumentException(message);
        }
    }
}
