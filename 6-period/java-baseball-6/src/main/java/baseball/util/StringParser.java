package baseball.util;

import baseball.error.AppException;
import baseball.error.ErrorMessage;
import java.util.Arrays;
import java.util.List;

public class StringParser {

    private static final int SPLIT_LIMIT = -1;

    private StringParser() {

    }

    public static int parseToInt(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new AppException(ErrorMessage.INVALID_WRONG_NUMBER_FORMAT);
        }
    }

    public static List<Integer> parseToNumberTokens(final String value, final String delimiter) {
        return Arrays.stream(value.split(delimiter, SPLIT_LIMIT))
                .map(StringParser::parseToInt)
                .toList();
    }
}
