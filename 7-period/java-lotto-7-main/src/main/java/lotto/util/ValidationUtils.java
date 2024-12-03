package lotto.util;

import static lotto.constant.ExceptionMessage.INVALID_NUMBER_TYPE_MESSAGE;
import static lotto.constant.ExceptionMessage.NULL_OR_EMPTY_MESSAGE;

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

    public static <T> void validateNull(T object) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException(NULL_OR_EMPTY_MESSAGE.getMessage());
        }
    }

    public static void validateInteger(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_TYPE_MESSAGE.getMessage());
        }
    }

    public static void validateLong(String value) {
        try {
            Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_TYPE_MESSAGE.getMessage());
        }
    }

    public static void validateDouble(String value) {
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_TYPE_MESSAGE.getMessage());
        }
    }
}
