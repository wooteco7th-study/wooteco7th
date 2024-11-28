package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_PRICE_DIVIDE_BY_TEN;
import static vendingmachine.exception.ErrorMessage.INVALID_PRICE_UNDER_TEN;

import vendingmachine.exception.CustomIllegalArgumentException;

public class HoldingPrice extends Price {

    public HoldingPrice(final int amount) {
        super(amount);
        validate(amount);
    }

    private void validate(final int amount) {
        if (amount < 10) {
            throw new CustomIllegalArgumentException(INVALID_PRICE_UNDER_TEN);
        }
        if (amount % 10 != 0) {
            throw new CustomIllegalArgumentException(INVALID_PRICE_DIVIDE_BY_TEN);
        }
    }
}
