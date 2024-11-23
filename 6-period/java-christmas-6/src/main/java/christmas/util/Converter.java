package christmas.util;

import static christmas.exception.ErrorMessage.INVALID_DAY;
import static christmas.exception.ErrorMessage.NOT_A_NUMBER;

import christmas.exception.CustomIllegalArgumentException;
import java.util.List;

public class Converter {

    public static List<Integer> convertToInteger(List<String> numbers) {
        InputValidator.validateNotNullOrEmpty(numbers);
        return numbers.stream()
                .map(Converter::convertToInteger)
                .toList();
    }

    public static int convertToInteger(final String input) {
        InputValidator.validateNotNullOrBlank(input);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException exception) {
            throw new CustomIllegalArgumentException(INVALID_DAY.getMessage());
        }
    }
}
