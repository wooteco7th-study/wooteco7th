package racingcar.util;

import java.util.List;
import racingcar.constant.ErrorMessage;

public class ListValidator {


    public static <T> void validateDuplicate(final List<T> values, final ErrorMessage errorMessage) {
        if (isDuplicatedElement(values)) {
            throw new IllegalArgumentException(errorMessage.getMessage());
        }
    }

    private static <T> boolean isDuplicatedElement(final List<T> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
    }

    private ListValidator() {

    }
}
