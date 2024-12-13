package christmas.dto;

import christmas.domain.OrderMenu;
import christmas.domain.OrderMenuGroup;
import java.util.List;

public record OrderMenuReceiptGroup(
        List<OrderMenuReceipt> orderMenuReceipts
) {

    public static OrderMenuReceiptGroup of(final OrderMenuGroup orderMenuGroup) {
        return new OrderMenuReceiptGroup(
                orderMenuGroup.getOrderMenus().stream()
                        .map(OrderMenuReceipt::of)
                        .toList()
        );
    }

    public record OrderMenuReceipt(
            String name,
            int quantity
    ){
        public static OrderMenuReceipt of(final OrderMenu orderMenu) {
            return new OrderMenuReceipt(
                    orderMenu.getMenu().getName(),
                    orderMenu.getQuantity()
            );
        }

    }
}
