package christmas.domain;

import christmas.exception.CustomIllegalArgumentException;
import christmas.exception.ErrorMessage;
import java.util.Objects;

public class Quantity {

    private static final int MIN_QUANTITY = 1;

    private final int value;

    public Quantity(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        if (value < MIN_QUANTITY) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quantity quantity)) {
            return false;
        }
        return getValue() == quantity.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    public int getValue() {
        return value;
    }
}
