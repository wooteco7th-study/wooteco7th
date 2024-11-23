package christmas.view.InputRequestVO;

import static christmas.view.InputRequestVO.Validator.check;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParseUtil {
    private static final String ERROR_NULL_OR_EMPTY = "[ERROR] 입력값이 null이거나 비어있습니다.";
    private static final String ERROR_INVALID_FORMAT = "[ERROR] 잘못된 형식입니다. 다시 입력해주세요.";

    private static final String REGEX_ANY_CHAR = ".*";
    private static final String REGEX_NOT_DELIMITERS = "[^%s%s]*";
    private static final String REGEX_SPACE = "\\s*";

    private static final String SINGLE_PATTERN_FORMAT = REGEX_SPACE + "%s" + REGEX_NOT_DELIMITERS + "%s" + REGEX_SPACE;
    private static final String FULL_PATTERN_FORMAT = "%s(%s%s)*";

    public static List<List<String>> parsePatterns(String input, Delimiter delimiter) {
        validateInput(input);
        return Arrays.stream(input.split(escapeDelimiter(delimiter.pattern())))
                .map(pattern -> parsePattern(pattern, delimiter))
                .collect(Collectors.toList());
    }

    private static void validateInput(String input) {
        check(input == null || input.trim().isEmpty())
                .withError(new IllegalArgumentException(ERROR_NULL_OR_EMPTY))
                .validate();
    }

    private static List<String> parsePattern(String pattern, Delimiter delimiter) {
        String cleaned = pattern.trim();
        return Arrays.stream(cleaned.split(escapeDelimiter(delimiter.value())))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private static String escapeDelimiter(String delimiter) {
        return Pattern.quote(delimiter);
    }
}
