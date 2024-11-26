package vendingmachine.domain;

import static vendingmachine.exception.ExceptionMessage.PRICE_OUT_OF_RANGE;
import static vendingmachine.exception.ExceptionMessage.REMAINDER_EXIST;

public class Product {

    public static final int MINIMUM_PRICE = 100;
    public static final int COIN_10_AMOUNT = 10;
    private static final int REMAINDER = 0;

    private final String name;
    private final int price;

    public Product(final String name, final int price) {
        validatePrice();
        this.name = name;
        this.price = price;
    }

    private void validatePrice() {
        validatePriceRange();
        validateRemainder();
    }

    private void validatePriceRange() {
        if (price < MINIMUM_PRICE) {
            throw new IllegalArgumentException(PRICE_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateRemainder() {
        if (price % COIN_10_AMOUNT != REMAINDER) {
            throw new IllegalArgumentException(REMAINDER_EXIST.getMessage());
        }
    }
}
