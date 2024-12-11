package lotto.domain;

import lotto.error.ErrorMessage;
import lotto.util.NumberValidator;

public class Money {

    public static final int UNIT_AND_TICKET_PRICE = 1_000;

    private final int value;

    public Money(final int value) {
        validate(value);
        this.value = value;
    }

    public int calculateQuotient(final int number) {
        return this.value / number;
    }

    private void validate(final int value) {
        NumberValidator.validateRange(value, UNIT_AND_TICKET_PRICE, Integer.MAX_VALUE, ErrorMessage.EXCEEDS_MONEY);
        NumberValidator.validateUnit(value, UNIT_AND_TICKET_PRICE, ErrorMessage.INVALID_MONEY_UNIT);
    }
}
