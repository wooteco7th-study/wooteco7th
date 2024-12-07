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


    public static <T> void validateSize(final List<T> values, final int min, final int max) {
        if (values.size() < min) {
            throw new AppException(ErrorMessage.INVALID_COACHES_MIN_SIZE);
        }
        if (values.size() > max) {
            throw new AppException(ErrorMessage.INVALID_COACHES_MAX_SIZE);
        }
    }


    private static <T> boolean isDuplicated(final List<T> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
    }

}
