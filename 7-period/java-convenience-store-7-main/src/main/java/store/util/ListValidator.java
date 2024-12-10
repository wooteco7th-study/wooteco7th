package store.util;

import java.util.List;
import store.error.AppException;
import store.error.ErrorMessage;

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
}
