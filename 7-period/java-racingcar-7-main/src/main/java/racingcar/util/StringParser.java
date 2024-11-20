package racingcar.util;

import java.util.Arrays;
import java.util.List;
import racingcar.constant.ErrorMessage;

public class StringParser {

    private static final int SPLIT_LIMIT = -1;

    public static List<String> parseToTokens(final String value, final String delimiter) {
        return Arrays.stream(value.split(delimiter, SPLIT_LIMIT))
                .toList();
    }

    public static int parseToInt(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_EXCEEDS_ATTEMPT_RANGE.getMessage());
        }
    }

}
