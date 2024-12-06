package vendingmachine.exception.repository;

import static vendingmachine.exception.ErrorMessage.INVALID_PRODUCT_NOT_FOUND;

public class ProductNotFoundException extends IllegalArgumentException {
    public ProductNotFoundException(final String productName) {
        super(INVALID_PRODUCT_NOT_FOUND.getMessage() + ": " + productName);
    }
}
