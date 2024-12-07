package bridge.util;

import static bridge.exception.ExceptionMessage.INVALID_NUMBER;

public class StringParser {

    private StringParser() {
    }

    public static int parseToValidNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER.getMessage());
        }
    }
}
