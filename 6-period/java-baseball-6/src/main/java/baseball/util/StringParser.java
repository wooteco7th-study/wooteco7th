package baseball.util;

import java.util.Arrays;
import java.util.List;

import static baseball.exception.ExceptionMessage.INVALID_NUMBER;

public class StringParser {

    private static final String DELIMITER = "";

    public static List<Integer> parseToList(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(StringParser::parseToInt)
                .toList();
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER.getMessage());
        }
    }
}
