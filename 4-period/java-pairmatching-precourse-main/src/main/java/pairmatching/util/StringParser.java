package pairmatching.util;

import java.util.Arrays;
import java.util.List;

public class StringParser {
    private StringParser() {
    }

    public static List<String> parseWithDelimiter(final String input, final String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .map(String::strip)
                .toList();
    }
}
