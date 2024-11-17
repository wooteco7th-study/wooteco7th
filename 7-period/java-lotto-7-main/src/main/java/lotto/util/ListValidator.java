package lotto.util;

import java.util.List;
import lotto.error.ErrorMessage;
import lotto.error.exception.InvalidListDuplicatedException;
import lotto.error.exception.InvalidListSizeException;

public class ListValidator {

    private ListValidator() {

    }

    public static <T> void validateDuplicate(final List<T> values, final ErrorMessage errorMessage) {
        if (isDuplicated(values)) {
            throw new InvalidListDuplicatedException(errorMessage);
        }
    }

    public static <T> void validateSize(final List<T> values, final int size, final ErrorMessage errorMessage) {
        if (values.size() != size) {
            throw new InvalidListSizeException(errorMessage);
        }
    }

    public static <T extends Number & Comparable<T>> void validateRange(final List<T> values, final ValidateFunction<T> function) {
        for (T value : values) {
            function.validate(value);
        }
    }

    private static <T> boolean isDuplicated(final List<T> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
    }

    @FunctionalInterface
    interface ValidateFunction <T extends Number>{
        void validate(final T value);
    }
}
