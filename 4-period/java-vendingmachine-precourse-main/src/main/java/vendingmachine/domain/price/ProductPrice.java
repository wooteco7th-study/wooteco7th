package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_AMOUNT;

import vendingmachine.util.NumberValidator;

public class ProductPrice extends Price {

    public ProductPrice(final int amount) {
        super(amount);
        validate(amount);
    }

    private void validate(final int amount) {
        NumberValidator.validateUnit(amount, 10, INVALID_AMOUNT);
        NumberValidator.validateRange(amount, 100, Integer.MAX_VALUE, INVALID_AMOUNT);
    }
}
