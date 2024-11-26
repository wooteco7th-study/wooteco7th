package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_INPUT_AMOUNT;

import vendingmachine.exception.CustomIllegalArgumentException;

public class InputPrice extends Price {
    public InputPrice(final long amount) {
        super(amount);
        validate(amount);
    }

    private void validate(final long amount) {
        if (amount == 0) {
            throw new CustomIllegalArgumentException(INVALID_INPUT_AMOUNT);
        }
    }
}
