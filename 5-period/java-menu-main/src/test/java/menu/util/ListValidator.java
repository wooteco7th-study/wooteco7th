package menu.util;

import java.util.List;
import menu.error.AppException;
import menu.error.ErrorMessage;

public class ListValidator {

    private ListValidator() {

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


    @FunctionalInterface
    public interface ValidateFunction<T extends Number> {
        void validate(final T value);
    }
}
