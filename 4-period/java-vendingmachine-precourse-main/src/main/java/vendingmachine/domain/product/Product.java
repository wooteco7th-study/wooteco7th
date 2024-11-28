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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price)
                && Objects.equals(quantity, product.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity);
    }

    public ProductPrice getPrice() {
        return price;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void subtractQuantity() {
        this.quantity = quantity.subtract(1);
    }
}
