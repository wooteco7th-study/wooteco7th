package pairmatching.util;

import java.util.Arrays;
import java.util.List;

public class StringParser {

    private static final int SPLIT_LIMIT = -1;
    private static final String BLANK = "";

        private StringParser() {

    }

    public static String removePattern(final String value, final String regex) {
        return value.replaceAll(regex, BLANK);
    }

    public static List<String> parseToTokens(final String value, final String delimiter) {
        return Arrays.stream(value.split(delimiter, SPLIT_LIMIT))
                .toList();
    }
}
