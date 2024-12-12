package oncall.domain.name;

import java.util.Objects;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public class Name {

    private static final int MAX_LENGTH = 5;

    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (value.length() > MAX_LENGTH) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
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
