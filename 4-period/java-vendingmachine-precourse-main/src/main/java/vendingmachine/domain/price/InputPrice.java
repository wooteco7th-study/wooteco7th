package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_AMOUNT;

import vendingmachine.exception.CustomIllegalArgumentException;

public class InputPrice extends Price {
    public InputPrice(final int amount) {
        super(amount);
        validate(amount);
    }

    private void validate(final int amount) {
        if (amount == 0) {
            throw new CustomIllegalArgumentException(INVALID_AMOUNT);
        }
    }
}
