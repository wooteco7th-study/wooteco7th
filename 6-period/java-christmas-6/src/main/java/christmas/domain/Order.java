package christmas.domain;

import christmas.exception.CustomIllegalArgumentException;
import christmas.exception.ErrorMessage;
import java.util.Objects;

public class Order {

    private static final int MIN_ORDER_QUANTITY = 1;

    private final Menu menu;
    private final int quantity;

    public Order(final Menu menu, final int quantity) {
        validate(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    private void validate(final int quantity) {
        if (quantity < MIN_ORDER_QUANTITY) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order order)) {
            return false;
        }
        return menu == order.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }

    public int getQuantity() {
        return quantity;
    }
}
