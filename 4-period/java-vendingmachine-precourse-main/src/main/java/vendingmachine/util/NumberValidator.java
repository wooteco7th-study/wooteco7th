package vendingmachine.util;

import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.ErrorMessage;

public class NumberValidator {

    private NumberValidator() {
    }

    public static <T extends Number & Comparable<T>> void validateRange(final T number, final T minInclusive,
                                                                        final T maxInclusive,
                                                                        final ErrorMessage errorMessage) {
        if (number.compareTo(minInclusive) < 0 || number.compareTo(maxInclusive) > 0) {
            throw new CustomIllegalArgumentException(errorMessage);
        }
    }

    public static void validateUnit(final int number, final int unit, final ErrorMessage errorMessage) {
        if (number % unit != 0) {
            throw new CustomIllegalArgumentException(errorMessage);
        }
    }
}
