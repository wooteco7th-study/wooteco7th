package christmas.util;

import static christmas.exception.ErrorMessage.INVALID_DAY;

import christmas.exception.CustomIllegalArgumentException;

public class Converter {

    public static int convertToInteger(final String input) {
        InputValidator.validateNotNullOrBlank(input);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException exception) {
            throw new CustomIllegalArgumentException(INVALID_DAY.getMessage());
        }
    }
}
