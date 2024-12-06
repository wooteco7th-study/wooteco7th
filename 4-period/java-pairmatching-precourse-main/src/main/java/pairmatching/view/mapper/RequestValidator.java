package pairmatching.view.mapper;

import java.util.regex.Pattern;

public class RequestValidator {
    private RequestValidator() {
    }

    public static void validateInput(String input, String pattern, String errorMessage) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (!Pattern.matches(pattern, input)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
