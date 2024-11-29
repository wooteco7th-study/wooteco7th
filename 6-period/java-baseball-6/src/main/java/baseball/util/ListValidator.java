package baseball.util;

import baseball.error.AppException;
import baseball.error.ErrorMessage;
import java.util.List;

public class ListValidator {

    private ListValidator() {

    }

    public static <T> void validateDuplicate(final List<T> values, final ErrorMessage errorMessage) {
        if (isDuplicated(values)) {
            throw new AppException(errorMessage);
        }
    }

    public static <T> void validateSize(final List<T> values, final int size, final ErrorMessage errorMessage) {
        if (values.size() != size) {
            throw new AppException(errorMessage);
        }
    }

    public static <T extends Number> void validateRange(final List<T> values, final ValidateFunction<T> function) {
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
    public interface ValidateFunction <T extends Number>{
        void validate(final T value);
    }
}
