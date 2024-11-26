package vendingmachine.domain;

import java.util.Objects;
import vendingmachine.error.ErrorMessage;
import vendingmachine.util.NumberValidator;

public class Product {

    private static final int PRICE_UNIT = 10;
    private static final int MIN_PRICE = 100;
    private static final int MAX_PRICE = Integer.MAX_VALUE;
    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = Integer.MAX_VALUE;

    private final String name;
    private final int price;
    private int quantity;

    public Product(final String name, final int price, final int quantity) {
        validate(price, quantity);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private void validate(final int price, final int quantity) {
        NumberValidator.validateRange(price, MIN_PRICE, MAX_PRICE, ErrorMessage.INVALID_PRODUCT_PRICE);
        NumberValidator.validateUnit(price, PRICE_UNIT, ErrorMessage.INVALID_PRODUCT_PRICE);
        NumberValidator.validateRange(quantity, MIN_QUANTITY, MAX_QUANTITY, ErrorMessage.INVALID_PRODUCT_QUANTITY);
    }

    public void subtractQuantity(final int quantity) {
        this.quantity -= quantity;
    }

    public boolean isOverProductPrice(final int price) {
        return price >= this.price;
    }

    public boolean hasQuantity() {
        return quantity > 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Product product)) {
            return false;
        }
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
