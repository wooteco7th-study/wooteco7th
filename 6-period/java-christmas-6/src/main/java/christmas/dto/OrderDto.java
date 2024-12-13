package christmas.dto;

import christmas.domain.menu.Menu;
import christmas.domain.order.Orders;
import java.util.List;
import java.util.Map;

public record OrderDto(String name, int quantity) {

    public static List<OrderDto> from(Orders orders) {
        return orders.getOrders().stream()
                .map(order -> new OrderDto(order.getMenu().name(), order.getQuantity()))
                .toList();
    }

    public static List<OrderDto> from(Map<Menu, Integer> gifts) {
        return gifts.entrySet().stream()
                .map(entry -> new OrderDto(entry.getKey().name(), entry.getValue()))
                .toList();
    }
}
