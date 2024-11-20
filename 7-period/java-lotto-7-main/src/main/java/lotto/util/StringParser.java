package lotto.util;

import java.util.Arrays;
import java.util.List;
import lotto.error.ErrorMessage;
import lotto.error.exception.InvalidNumberFormatException;

public class StringParser {

    private static final int SPLIT_LIMIT = -1;

    public static int parseToInt(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException(ErrorMessage.INVALID_WRONG_NUMBER_FORMAT);
        }
    }

    public static List<Integer> parseToNumberTokens(final String value, final String delimiter) {
        return Arrays.stream(value.split(delimiter, SPLIT_LIMIT))
                .map(StringParser::parseToInt)
                .toList();
    }

    private StringParser() {

    }
}
