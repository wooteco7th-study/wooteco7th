package racingcar.util;

import racingcar.constant.ErrorMessage;

public class NumberValidator {

    public static <T extends Number & Comparable<T>> void validateRange(final T number, final T min, final T max,
                                                                        final ErrorMessage errorMessage) {
        if (number.compareTo(min) < 0 || number.compareTo(max) > 0) {
            throw new IllegalArgumentException(errorMessage.getMessage());
        }
    }
}
