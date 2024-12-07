package menu.util;

import java.util.List;
import menu.error.AppException;
import menu.error.ErrorMessage;

public class ListValidator {

    private ListValidator() {

    }


    public static <T> void validateDuplicate(final List<T> values, final ErrorMessage errorMessage) {
        if (isDuplicated(values)) {
            throw new AppException(errorMessage);
        }
    }


    private static <T> boolean isDuplicated(final List<T> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
    }

    public static <T> void validateSize(final List<T> values, final int min, final int max,
                                        final ErrorMessage errorMessage) {
        if (values.size() < min || values.size() > max) {
            throw new AppException(errorMessage);
        }
    }

    public static <T extends Number> void validateRange(final List<T> values, final ValidateFunction<T> function) {
        for (T value : values) {
            function.validate(value);
        }
    }


    @FunctionalInterface
    public interface ValidateFunction<T extends Number> {
        void validate(final T value);
    }
}
