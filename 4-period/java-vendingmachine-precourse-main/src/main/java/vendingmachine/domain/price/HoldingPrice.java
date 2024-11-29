package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_PRICE_DIVIDE_BY_TEN;
import static vendingmachine.exception.ErrorMessage.INVALID_PRICE_ZERO;

import vendingmachine.exception.CustomIllegalArgumentException;

public class HoldingPrice extends Price {

    public HoldingPrice(final int amount) {
        super(amount);
        validate(amount);
    }

    private void validate(final int amount) {
        if (amount == 0) {
            throw new CustomIllegalArgumentException(INVALID_PRICE_ZERO);
        }
        if (amount % 10 != 0) {
            throw new CustomIllegalArgumentException(INVALID_PRICE_DIVIDE_BY_TEN);
        }
    }
}
