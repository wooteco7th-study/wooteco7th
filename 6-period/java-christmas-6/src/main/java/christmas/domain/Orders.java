package christmas.domain;

import christmas.exception.CustomIllegalArgumentException;
import christmas.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

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
}
