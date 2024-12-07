package menu.domain;

import static menu.exception.ExceptionMessage.INVALID_NAME_FORMAT;

public class CoachName {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 4;

    private final String name;

    public CoachName(final String name) {
        validateRange(name);
        this.name = name;
    }

    private void validateRange(String name) {
        if (isOutOfRange(name)) {
            throw new IllegalArgumentException(INVALID_NAME_FORMAT.getMessage());
        }
    }

    private boolean isOutOfRange(String name) {
        return name.length() < MIN_LENGTH || name.length() > MAX_LENGTH;
    }
}
