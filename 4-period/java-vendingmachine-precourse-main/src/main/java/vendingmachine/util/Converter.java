package vendingmachine.util;

import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.ErrorMessage;

public class Converter {

    public static int convertToInteger(final String input, final ErrorMessage message) {
        InputValidator.validateNotNullOrBlank(input, message);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException exception) {
            throw new CustomIllegalArgumentException(message);
        }
    }

    public static long convertToLong(final String input, final ErrorMessage message) {
        try {
            return Long.parseLong(input.trim());
        } catch (NumberFormatException exception) {
            throw new CustomIllegalArgumentException(message);
        }
    }
}
