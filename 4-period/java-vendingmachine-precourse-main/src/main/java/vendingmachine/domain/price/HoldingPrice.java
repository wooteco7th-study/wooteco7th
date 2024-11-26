package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_PRICE_DIVIDE_BY_TEN;
import static vendingmachine.exception.ErrorMessage.INVALID_PRICE_UNDER_EQUAL_HUNDRED;

import vendingmachine.exception.CustomIllegalArgumentException;

public class HoldingPrice extends Price {

    public HoldingPrice(final long amount) {
        super(amount);
        validate(amount);
    }

    private void validate(final long amount) {
        if (amount < 10) {
            throw new CustomIllegalArgumentException(INVALID_PRICE_UNDER_EQUAL_HUNDRED);
        }
        if (amount % 10 != 0) {
            throw new CustomIllegalArgumentException(INVALID_PRICE_DIVIDE_BY_TEN);
        }
    }
}
