package store.domain.order;

import java.util.Objects;
import store.domain.stock.Inventory;
import store.exception.CustomIllegalArgumentException;
import store.exception.ErrorMessage;

public class Order {

    private static final int MIN_EXCLUSIVE = 0;
    private final String name;
    private final int purchaseQuantity;

    public Order(final String name, final int quantity, final Inventory inventory) {
        validate(name, quantity, inventory);
        this.name = name;
        this.purchaseQuantity = quantity;
    }

    private void validate(final String name, final int quantity, final Inventory inventory) {
        if (notExistProduct(name, inventory)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_PRODUCT_NAME);
        }
        if (quantity < MIN_EXCLUSIVE) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
        inventory.validateAvailablePurchase(name, quantity);
    }


    private boolean notExistProduct(final String name, final Inventory inventory) {
        return !inventory.hasProduct(name);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order order)) {
            return false;
        }
        return Objects.equals(name, order.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }
}
