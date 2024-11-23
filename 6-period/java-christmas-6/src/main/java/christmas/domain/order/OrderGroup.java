package christmas.domain.order;

import christmas.error.AppException;
import christmas.error.ErrorMessage;
import christmas.util.ListValidator;
import christmas.util.NumberValidator;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OrderGroup {

    private final List<Order> orders;

    public OrderGroup(final List<Order> orders) {
        validate(orders);
        this.orders = orders;
    }

    public long calculateOrdersTotalPrice() {
        return orders.stream()
                .mapToInt(Order::calculateOrderPrice)
                .sum();
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public int countMenuType(final MenuType menuType) {
        return (int) orders.stream()
                .filter(order -> Objects.equals(order.getMenuType(), menuType))
                .count();
    }

    private void validate(final List<Order> orders) {
        ListValidator.validateDuplicate(orders, ErrorMessage.INVALID_WRONG_ORDER_FORMAT);
        NumberValidator.validateRange(calculateOrderTotalQuantity(orders), Order.MIN_QUANTITY, Order.MAX_QUANTITY,
                ErrorMessage.INVALID_WRONG_ORDER_FORMAT);
        validateOrderOnlyDrink(orders);

    }

    private void validateOrderOnlyDrink(final List<Order> orders) {
        if (isOrderOnlyDrink(orders)) {
            throw new AppException(ErrorMessage.INVALID_WRONG_ORDER_FORMAT);
        }
    }

    private boolean isOrderOnlyDrink(final List<Order> orders) {
        return orders.stream()
                .allMatch(order -> Objects.equals(order.getMenuType(), MenuType.DRINK));
    }

    private int calculateOrderTotalQuantity(final List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();
    }

}
