package vendingmachine.exception.repository;

import static vendingmachine.exception.ErrorMessage.INVALID_PRODUCT_DUPLICATE;

public class DuplicateProductException extends IllegalArgumentException {
    public DuplicateProductException(final String name) {
        super(INVALID_PRODUCT_DUPLICATE.getMessage() + ": " + name);
    }
}
