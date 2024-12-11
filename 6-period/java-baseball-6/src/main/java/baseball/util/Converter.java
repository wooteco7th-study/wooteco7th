package baseball.util;

import baseball.exception.ExceptionMessage;

public class Converter {

    private static final char START_NUMBER = '0';

    public static int convertToInteger(char number) {
        if (!Character.isDigit(number)) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_A_NUMBER.getMessage());
        }
        return number - START_NUMBER;
    }
}
