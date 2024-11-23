package christmas.domain;

import christmas.error.ErrorMessage;
import christmas.util.NumberValidator;
import java.util.Objects;

public class Order {

    public static final int MIN_QUANTITY = 1;
    public static final int MAX_QUANTITY = 20;
    private final Menu menu;
    private final int quantity;

    public Order(final Menu menu, final int quantity) {
        validate(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    public int calculateOrderPrice() {
        return menu.getPrice() * quantity;
    }

    private void validate(final int quantity) {
        NumberValidator.validateRange(quantity, MIN_QUANTITY, MAX_QUANTITY, ErrorMessage.INVALID_WRONG_ORDER_FORMAT);
    }

    public MenuType getMenuType() {
        return menu.getMenuType();
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Order order)) {
            return false;
        }
        return menu == order.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(menu);
    }
}
