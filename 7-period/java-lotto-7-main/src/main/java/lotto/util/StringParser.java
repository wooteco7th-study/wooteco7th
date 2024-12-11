package lotto.util;

import java.util.Arrays;
import java.util.List;
import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class StringParser {

    private static final int SPLIT_LIMIT = -1;
    private static final String BLANK = "";

    private StringParser() {

    }

    public static String removePattern(final String value, final String regex) {
        return value.replaceAll(regex, BLANK);
    }

    public static int parseToInt(final String value, final ErrorMessage errorMessage) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new AppException(errorMessage);
        }
    }

    public static List<Integer> parseToNumberTokens(final String value, final String delimiter, final ErrorMessage errorMessage) {
        return Arrays.stream(value.split(delimiter, SPLIT_LIMIT))
                .map(token -> StringParser.parseToInt(token, errorMessage))
                .toList();
    }

    public static List<String> parseToTokens(final String value, final String delimiter) {
        return Arrays.stream(value.split(delimiter, SPLIT_LIMIT))
                .toList();
    }
}
