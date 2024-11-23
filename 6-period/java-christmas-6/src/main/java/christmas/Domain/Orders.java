package christmas.Domain;

import christmas.Exception.InputException;
import java.util.List;

public class Orders {

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateTotalCount(orders);
        validateOnlyDrink(orders);
        validateMenuDuplicate(orders);
        this.orders = orders;
    }

    private void validateMenuDuplicate(List<Order> orders) {
        if (isDuplicate(orders)) {
            throw new InputException();
        }
    }

    private boolean isDuplicate(List<Order> orders) {
        return orders.stream().map(Order::getMenu).distinct().toList().size() != orders.size();
    }

    private void validateOnlyDrink(List<Order> orders) {
        if (orders.size() == countDrink(orders)) {
            throw new InputException();
        }
    }

    private int countDrink(List<Order> orders) {
        return orders.stream().filter(Order::isDrink).toList().size();
    }

    public int countMain() {
        return orders.stream().filter(Order::isMain).toList().size();
    }

    public int countDessert() {
        return orders.stream().filter(Order::isDessert).toList().size();
    }

    private void validateTotalCount(List<Order> orders) {
        int totalCount = calculateTotalCount(orders);
        if (totalCount > 20) {
            throw new InputException();
        }
    }

    private int calculateTotalCount(List<Order> orders) {
        return orders.stream().mapToInt(Order::getCount).sum();
    }

    public int calculateTotalPrice() {
        return orders.stream().mapToInt(Order::getPrice).sum();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        orders.forEach(order -> { stringBuilder.append(order.toString()).append("\n"); });
        return stringBuilder.toString();
    }
}
