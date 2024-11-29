package vendingmachine.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static vendingmachine.exception.ExceptionMessage.AMOUNT_MUST_BE_POSITIVE;
import static vendingmachine.exception.ExceptionMessage.INVALID_AMOUNT;

public class StringParser {

    private StringParser() {
    }

    public static List<String> parseWithDelimiter(final String input, final String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .collect(Collectors.toList());
    }

    public static int parseToValidNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            validatePositiveNumber(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_AMOUNT.getMessage());
        }
    }

    private static void validatePositiveNumber(final int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(AMOUNT_MUST_BE_POSITIVE.getMessage());
        }
    }
}

