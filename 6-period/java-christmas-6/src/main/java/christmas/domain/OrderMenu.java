package christmas.domain;

import java.util.Objects;

public class OrderMenu {

    private final Menu menu;
    private final Quantity quantity;

    public OrderMenu(final Menu menu, final Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public boolean hasSameMenu(final Menu menu) {
        return this.menu.equals(menu);
    }

    public int getQuantity() {
        return quantity.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final OrderMenu orderMenu)) return false;
        return Objects.equals(this.menu, orderMenu.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}
