package baseball.util;

import static baseball.exception.ExceptionMessage.INVALID_NUMBER;

public class StringParser {

    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER.getMessage());
        }
    }
}
