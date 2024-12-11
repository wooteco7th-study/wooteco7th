package lotto.util;

import java.util.List;
import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class NumberValidator {

    private NumberValidator() {

    }

    public static <T extends Number & Comparable<T>> void validateRange(final T number, final T min, final T max, final
    ErrorMessage errorMessage) {
        if (isExceedsSize(number, min, max)) {
            throw new AppException(errorMessage);
        }
    }

    public static void validateUnit(final int value, final int unit, final ErrorMessage errorMessage) {
        if (isInvalidUnit(value, unit)) {
            throw new AppException(errorMessage);
        }
    }

    public static <T> void validateDuplicate(final T value, final List<T> values, ErrorMessage errorMessage) {
        if (values.contains(value)) {
            throw new AppException(errorMessage);
        }
    }

    private static boolean isInvalidUnit(final int value, final int unit) {
        return value % unit != 0;
    }

    private static <T extends Number & Comparable<T>> boolean isExceedsSize(final T number, final T min, final T max) {
        return number.compareTo(min) < 0 || number.compareTo(max) > 0;
    }
}
