package menu.domain;

import menu.error.ErrorMessage;
import menu.util.NumberValidator;

public class CoachName {

    private static final int MIN = 2;
    private static final int MAX = 4;
    private final String value;

    public CoachName(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        NumberValidator.validateRange(value.length(), MIN, MAX, ErrorMessage.INVALID_COACH_NAME);
    }

    public String getValue() {
        return value;
    }
}
