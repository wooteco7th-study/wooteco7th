package oncall.util;

import java.util.regex.Pattern;
import oncall.error.AppException;
import oncall.error.ErrorMessage;

public class StringValidator {

    private static final Pattern ORDER_MENU_PATTERN = Pattern.compile("^\\d+,.+$");

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
