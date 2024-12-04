package subway.util;

import static subway.message.ExceptionMessage.NULL_OR_EMPTY_MESSAGE;

import java.util.List;
import java.util.Objects;

public class ValidationUtils {

    public static void validateNullOrEmpty(String value) {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY_MESSAGE.getMessage());
        }
    }

    public static void validateNullOrEmpty(List<?> list) {
        if (Objects.isNull(list) || list.isEmpty()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY_MESSAGE.getMessage());
        }
    }

}
