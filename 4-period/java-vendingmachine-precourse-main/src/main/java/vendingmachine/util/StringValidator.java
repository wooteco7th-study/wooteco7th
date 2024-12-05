package vendingmachine.util;

import java.util.regex.Pattern;
import vendingmachine.error.AppException;
import vendingmachine.error.ErrorMessage;

public class StringValidator {

    private static final Pattern ORDER_MENU_PATTERN = Pattern.compile("^\\[[가-힣a-zA-Z]+,\\d+,\\d+]$");

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
