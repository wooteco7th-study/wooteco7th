package vendingmachine.exception;

import static vendingmachine.exception.ErrorMessage.INVALID_PRODUCT_PRICE;

public class InvalidProductPriceException extends IllegalArgumentException {
    public InvalidProductPriceException() {
        super(INVALID_PRODUCT_PRICE.getMessage());
    }
}
