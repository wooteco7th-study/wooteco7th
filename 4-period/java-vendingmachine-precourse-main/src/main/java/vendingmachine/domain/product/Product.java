package vendingmachine.domain.product;

import java.util.Objects;
import vendingmachine.domain.price.ProductPrice;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.ErrorMessage;

public class Product {

    private final String name;
    private final ProductPrice price;
    private Quantity quantity;

    public Product(final String name, final ProductPrice price, final Quantity quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        validate(quantity);
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    public void subtractQuantity() {
        this.quantity = quantity.subtract(1);
    }

    private void validate(final Quantity quantity) {
        if (quantity.isZero()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_HOLDING_PRODUCT_QUANTITY);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product product)) {
            return false;
        }
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getPriceAmount() {
        return price.getAmount();
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
