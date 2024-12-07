package menu.domain.name;

import java.util.Objects;
import java.util.regex.Pattern;
import menu.exception.CustomIllegalArgumentException;
import menu.exception.ErrorMessage;
import menu.util.InputValidator;

public class Name {

    private static final String ONLY_TEXT_REGEX = "[^\\d]+";
    private static final Pattern PATTERN = Pattern.compile(ONLY_TEXT_REGEX);

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (value.length() < 2 || value.length() > 4) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_COACH_NAME);
        }
        if (InputValidator.isInvalidPattern(value, PATTERN)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_COACH_NAME);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name name)) {
            return false;
        }
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getValue() {
        return value;
    }
}
