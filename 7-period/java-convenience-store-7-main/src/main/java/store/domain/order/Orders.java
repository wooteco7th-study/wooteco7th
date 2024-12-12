package store.domain.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import store.exception.CustomIllegalArgumentException;
import store.exception.ErrorMessage;

public class Orders {

    private final List<Order> orders;

    public Orders(final List<Order> orders) {
        validate(orders);
        this.orders = new ArrayList<>(orders);
    }

    private void validate(final List<Order> orders) {
        validateDuplicated(orders);
    }

    private void validateDuplicated(final List<Order> orders) {
        if (orders.size() != orders.stream().distinct().count()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }
}
