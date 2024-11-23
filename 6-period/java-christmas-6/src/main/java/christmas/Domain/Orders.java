package christmas.Domain;

import java.util.List;

public class Orders {

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateTotalCount(orders);
        this.orders = orders;
    }

    //menu 중복 검사
    //음료만 주문했는지 검사

    private void validateTotalCount(List<Order> orders) {
        int totalCount = calculateTotalCount();
        if (totalCount > 20) {
            throw new IllegalArgumentException();
        }
    }

    private int calculateTotalCount() {
        return orders.stream().mapToInt(Order::getCount).sum();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        orders.forEach(order -> { stringBuilder.append(order.toString()).append("\n"); });
        return stringBuilder.toString();
    }
}
