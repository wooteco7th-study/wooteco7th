package christmas.dto;

import christmas.domain.Order;

public record OrderMenuReceipt(
        String name,
        int quantity
) {
    public static OrderMenuReceipt of(final Order order) {
        return new OrderMenuReceipt(order.getMenuName(), order.getQuantity());
    }
}
