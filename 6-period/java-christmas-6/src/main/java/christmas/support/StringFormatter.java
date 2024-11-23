package christmas.support;

import christmas.domain.Order;
import christmas.domain.Orders;

public class StringFormatter {

    private static final String FORMAT = "%s %d개" + System.lineSeparator();

    public String makeMessage(final Orders orders) {
        StringBuilder message = new StringBuilder();
        for (Order order : orders.getOrders()) {
            message.append(String.format(FORMAT, order.getMenu().getName(), order.getQuantity().getValue()));
        }
        return message.toString();
    }
}
