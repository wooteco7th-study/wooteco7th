package oncall.util;

import java.util.Arrays;
import java.util.List;
import oncall.error.AppException;
import oncall.error.ErrorMessage;

public class StringParser {

    private static final String BLANK = "";
    private static final int SPLIT_LIMIT = -1;

    public static int parseToNumber(final String value, final ErrorMessage errorMessage) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new AppException(errorMessage);
        }
    }


    public static List<String> parseToTokens(final String value, final String delimiter) {
        return Arrays.stream(value.split(delimiter, SPLIT_LIMIT))
                .toList();
    }

    public static List<Integer> parseToNumbers(final String value, final String delimiter,
                                               final ErrorMessage errorMessage) {
        return Arrays.stream(value.split(delimiter, SPLIT_LIMIT))
                .map(token -> StringParser.parseToNumber(token, errorMessage))
                .toList();
    }

    public static String removeAllPattern(final String value, final String regex) {
        return value.replaceAll(regex, BLANK);
    }
}
