package pairmatching.util;

import java.util.List;
import pairmatching.error.AppException;
import pairmatching.error.ErrorMessage;

public class ListValidator {

    private ListValidator() {

    }

    public static <T> void validateSize(final List<T> values, final int size, final ErrorMessage errorMessage) {
        if (values.size() != size) {
            throw new AppException(errorMessage);
        }
    }
}
