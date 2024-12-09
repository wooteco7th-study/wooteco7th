package oncall.util;

import java.util.Arrays;
import java.util.List;
import oncall.error.AppException;
import oncall.error.ErrorMessage;

public class StringParser {

    private StringParser() {

    }


    public static int parseToInt(final String value, final ErrorMessage errorMessage) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new AppException(errorMessage);
        }
    }


    public static List<String> parseToTokens(final String value, final String delimiter) {
        return Arrays.stream(value.split(delimiter))
                .toList();
    }
}
