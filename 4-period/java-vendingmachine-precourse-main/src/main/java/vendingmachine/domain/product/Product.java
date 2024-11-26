package vendingmachine.domain.product;

import vendingmachine.domain.price.ProductPrice;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.ErrorMessage;

public class Product {

    private final String name;
    private final ProductPrice price;
    private final Quantity quantity;

    public Product(final String name, final ProductPrice price, final Quantity quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        validate(quantity);
    }

    private void validate(final Quantity quantity) {
        if (quantity.isZero()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_HOLDING_PRODUCT_QUANTITY);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
