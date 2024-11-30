package bridge.util;

import bridge.error.CustomIllegalArgumentException;
import bridge.error.ErrorMessage;

public class StringParser {
    
    private static final String BLANK = "";

        private StringParser() {

    }

    public static String removePattern(final String value, final String regex) {
        return value.replaceAll(regex, BLANK);
    }

    public static int parseToInt(final String value, final ErrorMessage errorMessage) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new CustomIllegalArgumentException(errorMessage);
        }
    }

}
