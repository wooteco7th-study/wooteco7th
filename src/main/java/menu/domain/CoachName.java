package menu.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final CoachName coachName)) {
            return false;
        }
        return Objects.equals(name, coachName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
