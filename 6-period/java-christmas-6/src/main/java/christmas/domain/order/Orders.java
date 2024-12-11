package christmas.domain.order;

import christmas.domain.Menu.MenuType;
import christmas.domain.Quantity;
import christmas.exception.CustomIllegalArgumentException;
import christmas.exception.ErrorMessage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Orders {

    private static final int MAX_MENU_QUANTITY = 20;

    private final List<Order> orders;

    public Orders(final List<Order> orders) {
        checkDuplicated(orders);
        checkHasDrinkMenus(orders);
        checkIfQuotaExceeded(orders);
        this.orders = new ArrayList<>(orders);
    }

    private void checkDuplicated(final List<Order> orders) {
        int uniqueMenuCount = (int) orders.stream()
                .distinct()
                .count();
        if (uniqueMenuCount != orders.size()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void checkHasDrinkMenus(final List<Order> orders) {
        if (countDrinkMenu(orders) == orders.size()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void checkIfQuotaExceeded(final List<Order> orders) {
        int size = orders.stream()
                .map(Order::getQuantity)
                .mapToInt(Quantity::getValue)
                .sum();
        if (size > MAX_MENU_QUANTITY) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }
    }

    private int countDrinkMenu(final List<Order> orders) {
        return (int) orders.stream()
                .filter(order -> order.hasMenuType(MenuType.DRINK))
                .count();
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
            price = price.add(order.calculateTotalPrice());
        }
        return price;
    }

    public boolean isTotalPriceOverThan(final BigDecimal compared) {
        return calculateTotalPrice().compareTo(compared) >= 0;
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}
