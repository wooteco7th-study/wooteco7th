package christmas.domain;

import christmas.exception.CustomIllegalArgumentException;
import christmas.exception.ErrorMessage;
import java.util.List;

public class Orders {

    private static final int MAX_TOTAL_ORDER_QUANTITY = 20;

    private final List<Order> orders;

    public Orders(final List<Order> orders) {
        validate(orders);
        this.orders = orders;
    }

    private void validate(final List<Order> orders) {
        validateDuplicatedMenu(orders);
        validateTotalQuantity(orders);
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
}
