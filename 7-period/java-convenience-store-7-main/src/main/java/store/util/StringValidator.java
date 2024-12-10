package store.util;

import java.util.regex.Pattern;
import store.error.AppException;
import store.error.ErrorMessage;

public class StringValidator {

    private static final Pattern ORDER_MENU_PATTERN = Pattern.compile("^\\[[가-힣a-zA-Z]+-\\d+]$");

    private StringValidator() {

    }

    public static void validateFormat(final String value, final ErrorMessage errorMessage) {
        if (isInvalidFormat(value)) {
            throw new AppException(errorMessage);
        }
    }

    private static boolean isInvalidFormat(final String value) {
        return !ORDER_MENU_PATTERN.matcher(value).matches();
    }
}
