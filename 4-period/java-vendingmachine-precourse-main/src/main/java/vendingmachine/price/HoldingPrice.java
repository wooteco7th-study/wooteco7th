package vendingmachine.price;

import static vendingmachine.exception.ErrorMessage.INVALID_HOLDING_PRICE_DIVIDE_BY_TEN;
import static vendingmachine.exception.ErrorMessage.INVALID_HOLDING_PRICE_UNDER_TEN;

import vendingmachine.exception.CustomIllegalArgumentException;

public class HoldingPrice extends Price {

    public HoldingPrice(final long amount) {
        super(amount);
        validate(amount);
    }

    private void validate(final long amount) {
        if (amount < 10) {
            throw new CustomIllegalArgumentException(INVALID_HOLDING_PRICE_UNDER_TEN);
        }
        if (amount % 10 != 0) {
            throw new CustomIllegalArgumentException(INVALID_HOLDING_PRICE_DIVIDE_BY_TEN);
        }
    }
}
