package lotto.util;

import lotto.error.ErrorMessage;
import lotto.error.exception.InvalidNumberFormatException;
import lotto.error.exception.InvalidNumberRangeException;

public class NumberValidator {

    private NumberValidator() {
        
    }

    public static <T extends Number & Comparable<T>> void validateRange(final T number, final T min, final T max, final
                                                                        ErrorMessage errorMessage) {
        if(isExceedsSize(number, min, max)) {
            throw new InvalidNumberRangeException(errorMessage);
        }
    }

    public static void validateUnit(final int value, final int unit, final ErrorMessage errorMessage) {
        if(isInvalidUnit(value, unit)) {
            throw new InvalidNumberFormatException(errorMessage);
        }
    }

    private static boolean isInvalidUnit(final int value, final int unit) {
        return value % unit != 0;
    }

    private static <T extends Number & Comparable<T>> boolean isExceedsSize(final T number, final T min, final T max) {
        return number.compareTo(min) < 0 || number.compareTo(max) > 0;
    }
}
