package lotto.view.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.util.ValidationUtils;

public class RequestParser {
    private RequestParser() {
    }

    public static List<String[]> parseList(String input) {
        return Arrays.stream(input.replaceAll("[\\[\\]]", "").split(";"))
                .map(item -> item.trim().split(","))
                .map(parts -> Arrays.stream(parts)
                        .map(String::trim)
                        .toArray(String[]::new))
                .collect(Collectors.toList());
    }

    public static List<Integer> parserList(String input) {
        return Arrays.stream(input.trim().split(","))
                .map(RequestParser::parseToInt)
                .toList();
    }

    public static int parseToInt(String input) {
        ValidationUtils.validateInteger(input);
        return Integer.parseInt(input.trim());
    }

}
