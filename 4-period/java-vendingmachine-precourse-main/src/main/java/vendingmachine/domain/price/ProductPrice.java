package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_PRICE_DIVIDE_BY_TEN;
import static vendingmachine.exception.ErrorMessage.INVALID_PRICE_UNDER_EQUAL_HUNDRED;

import vendingmachine.exception.CustomIllegalArgumentException;

public class ProductPrice extends Price {

    public ProductPrice(final int amount) {
        super(amount);
        validate(amount);
    }

    private void validate(final int amount) {
        if (amount % 10 != 0) {
            throw new CustomIllegalArgumentException(INVALID_PRICE_DIVIDE_BY_TEN);
        }
        if (amount < 100) {
            throw new CustomIllegalArgumentException(INVALID_PRICE_UNDER_EQUAL_HUNDRED);
        }
    }
}
