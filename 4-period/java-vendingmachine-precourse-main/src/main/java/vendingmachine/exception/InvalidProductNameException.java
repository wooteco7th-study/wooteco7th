package vendingmachine.exception;

import static vendingmachine.exception.ErrorMessage.INVALID_PRODUCT_NAME_EMPTY;

public class InvalidProductNameException extends IllegalArgumentException {
    public InvalidProductNameException() {
        super(INVALID_PRODUCT_NAME_EMPTY.getMessage());
    }
}
