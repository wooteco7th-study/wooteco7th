package vendingmachine.exception;

import static vendingmachine.exception.ErrorMessage.INVALID_PRODUCT_DECREASE;

public class SoldOutException extends IllegalArgumentException {
    public SoldOutException() {
        super(INVALID_PRODUCT_DECREASE.getMessage());
    }
}
