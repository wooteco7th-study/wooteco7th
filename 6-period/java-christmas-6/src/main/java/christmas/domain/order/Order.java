package christmas.domain.order;

import christmas.domain.Menu;
import christmas.domain.Menu.MenuType;
import christmas.domain.Quantity;
import java.math.BigDecimal;
import java.util.Objects;

public class Order {

    private final Menu menu;
    private final Quantity quantity;

    public Order(final Menu menu, final Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public boolean hasMenuType(MenuType menuType) {
        return menu.getType().equals(menuType);
    }

    public String getMenuName() {
        return menu.name();
    }

    public BigDecimal calculateTotalPrice() {
        return menu.getPrice().multiply(BigDecimal.valueOf(quantity.getValue()));
    }

    public Quantity getQuantity() {
        return quantity;
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
}
