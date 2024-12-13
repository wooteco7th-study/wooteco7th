package christmas.domain.order;

import christmas.domain.menu.MenuType;
import christmas.exception.CustomIllegalArgumentException;
import christmas.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Orders {

    private static final int MAX_TOTAL_ORDER_QUANTITY = 20;

    private final List<Order> orders;

    public Orders(final List<Order> orders) {
        validate(orders);
        this.orders = new ArrayList<>(orders);
    }

    public int calculateTotalPrice() {
        return orders.stream()
                .mapToInt(Order::calculatePrice)
                .sum();
    }

    public int calculateMenuQuantity(MenuType menuType) {
        return orders.stream()
                .filter(order -> order.hasSameMenuType(menuType))
                .mapToInt(Order::getQuantity)
                .sum();
    }

    private void validate(final List<Order> orders) {
        validateDuplicatedMenu(orders);
        validateTotalQuantity(orders);
        validateOnlyDrinks(orders);
    }

    private void validateOnlyDrinks(final List<Order> orders) {
        List<MenuType> menuTypes = getMenuTypes(orders);
        if (hasOnlyDrinks(menuTypes)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }
    }

    private boolean hasOnlyDrinks(final List<MenuType> menuTypes) {
        return menuTypes.size() == 1 && menuTypes.getFirst().equals(MenuType.음료);
    }

    private List<MenuType> getMenuTypes(final List<Order> orders) {
        return orders.stream()
                .map(Order::getMenuType)
                .distinct()
                .toList();
    }

    private void validateTotalQuantity(final List<Order> orders) {
        int total = calculateTotalQuantity(orders);
        if (total > MAX_TOTAL_ORDER_QUANTITY) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }
    }

    private int calculateTotalQuantity(final List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();
    }

    private void validateDuplicatedMenu(final List<Order> orders) {
        if (orders.size() != orders.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_ORDER);
        }
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}
