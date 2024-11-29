package vendingmachine.domain.product;

import static vendingmachine.exception.ErrorMessage.INVALID_PRODUCT_QUANTITY;

import vendingmachine.exception.CustomIllegalArgumentException;

public class Quantity {

    private final int quantity;

    public Quantity(final int quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    public boolean isZero() {
        return quantity == 0;
    }

    public boolean hasStock() {
        return quantity > 0;
    }

    private void validate(final int quantity) {
        if (quantity < 0) {
            throw new CustomIllegalArgumentException(INVALID_PRODUCT_QUANTITY);
        }
    }

    public Quantity subtract(final int subtracted) {
        return new Quantity(quantity - subtracted);
    }
}
