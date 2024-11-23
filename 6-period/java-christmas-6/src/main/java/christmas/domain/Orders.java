package christmas.domain;

import christmas.domain.Menu.MenuType;
import christmas.exception.CustomIllegalArgumentException;
import christmas.exception.ErrorMessage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Orders {

    private final List<Order> orders;

    public Orders(final List<Order> orders) {
        this.orders = new ArrayList<>(orders);
    }

    public void add(Order addedOrder) {
        for (Order order : orders) {
            if (order.hasSameMenu(addedOrder)) {
                throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
            }
        }
        orders.add(addedOrder);
    }

    public boolean checkOnlyDrinks() {
        Set<MenuType> menus = new HashSet<>();
        for (Order order : orders) {
            menus.add(order.getMenu().getType());
        }
        return menus.size() == 1 && menus.contains(MenuType.DRINK);
    }

    public int countSameTypeMenu(MenuType menuType) {
        int count = 0;
        for (Order order : orders) {
            if (order.hasMenuType(menuType)) {
                count += order.getQuantity().getValue();
            }
        }
        return count;
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal price = BigDecimal.ZERO;
        for (Order order : orders) {
            price = price.add(order.getMenu().getPrice().multiply(new BigDecimal(order.getQuantity().getValue())));
        }
        return price;
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}
