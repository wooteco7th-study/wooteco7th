package lotto.domain;

import lotto.exception.CustomIllegalArgumentException;
import lotto.exception.ErrorMessage;

public class Amount {

    private static final int LOTTO_UNIT_PRICE = 1000;
    private static final int MIN_EXCLUSIVE = 0;

    private final int value;

    public Amount(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        if (isNotUnitNumber(value) || isSmallerThanMin(value)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    private boolean isSmallerThanMin(final int value) {
        return value <= MIN_EXCLUSIVE;
    }

    private boolean isNotUnitNumber(final int value) {
        return value % LOTTO_UNIT_PRICE != MIN_EXCLUSIVE;
    }
}
