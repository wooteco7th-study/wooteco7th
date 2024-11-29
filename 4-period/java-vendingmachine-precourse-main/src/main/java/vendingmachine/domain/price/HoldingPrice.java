package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_HOLDING_AMOUNT;

import vendingmachine.util.NumberValidator;

public class HoldingPrice extends Price {

    public HoldingPrice(final int amount) {
        super(amount);
        validate(amount);
    }

    private void validate(final int amount) {
        NumberValidator.validateRange(amount, 0, Integer.MAX_VALUE, INVALID_HOLDING_AMOUNT);
        NumberValidator.validateUnit(amount, 10, INVALID_HOLDING_AMOUNT);
    }
}
