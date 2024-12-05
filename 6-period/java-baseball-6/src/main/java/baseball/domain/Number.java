package baseball.domain;

import java.util.Objects;

import static baseball.exception.ExceptionMessage.NUMBER_OUT_OF_RANGE;

public class Number {

    public static final int MIN_RANGE = 1;
    public static final int MAX_RANGE = 9;
    private final int value;

    public Number(final int value) {
        validateRange(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validateRange(int value) {
        if (value < MIN_RANGE || value > MAX_RANGE) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Number number = (Number) o;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
