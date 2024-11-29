package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_AMOUNT;

import vendingmachine.util.NumberValidator;

public class HoldingPrice extends Price {

    public HoldingPrice(final int amount) {
        super(amount);
        validate(amount);
    }

    private void validate(final int amount) {
        NumberValidator.validateUnit(amount, 10, INVALID_AMOUNT);
    }
}
