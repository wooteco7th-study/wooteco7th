package christmas.dto;

import christmas.domain.order.Order;

public record OrderMenuDto(String name, int quantity) {

    public static OrderMenuDto of(Order order) {
        return new OrderMenuDto(order.getMenuName(), order.getQuantity().getValue());
    }
}
