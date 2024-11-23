package christmas.domain;

import java.util.Objects;

public class Order {

    private final Menu menu;
    private final Quantity quantity;

    public Order(final Menu menu, final Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public boolean hasSameMenu(final Order order) {
        return this.menu.equals(order.getMenu());
    }

    private Menu getMenu() {
        return menu;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return quantity == order.quantity && menu == order.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, quantity);
    }
}
