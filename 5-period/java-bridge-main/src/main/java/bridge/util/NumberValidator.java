package bridge.util;

import bridge.error.CustomIllegalArgumentException;
import bridge.error.ErrorMessage;

public class NumberValidator {

    private NumberValidator() {

    }

    public static <T extends Number & Comparable<T>> void validateRange(final T number, final T min, final T max, final
    ErrorMessage errorMessage) {
        if (isExceedsSize(number, min, max)) {
            throw new CustomIllegalArgumentException(errorMessage);
        }
    }

    private static <T extends Number & Comparable<T>> boolean isExceedsSize(final T number, final T min, final T max) {
        return number.compareTo(min) < 0 || number.compareTo(max) > 0;
    }
}
