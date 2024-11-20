package baseball.domain.baseball;

import baseball.exception.ExceptionMessage;
import java.util.Objects;

public class BaseballNumber {

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 9;

    private int value;

    public BaseballNumber(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.OUT_OF_RANGE.getMessage(MIN_NUMBER, MAX_NUMBER));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseballNumber that)) {
            return false;
        }
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
