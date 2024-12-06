package vendingmachine.exception;

import static vendingmachine.exception.ErrorMessage.INVALID_PRODUCT_QUANTITY;

public class InvalidProductQuantityException extends IllegalArgumentException {
    public InvalidProductQuantityException() {
        super(INVALID_PRODUCT_QUANTITY.getMessage());
    }
}
