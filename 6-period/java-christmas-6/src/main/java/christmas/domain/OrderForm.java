package christmas.domain;

import java.util.List;

import static christmas.exception.ExceptionMessage.INVALID_ORDER;

public class OrderForm {

    public static final int MAX_QUANTITY = 20;

    private final VisitDate orderDate;
    private final List<OrderMenu> menus;

    public OrderForm(final VisitDate orderDate, final List<OrderMenu> menus) {
        validateDuplicateMenu(menus);
        validateMenuCategory(menus);
        validateOrderQuantity(menus);
        this.orderDate = orderDate;
        this.menus = menus;
    }

    public int getTotalPriceBeforeDiscount() {
        return menus.stream()
                .mapToInt(OrderMenu::getTotalPrice)
                .sum();
    }

    public VisitDate getOrderDate() {
        return orderDate;
    }

    private void validateDuplicateMenu(List<OrderMenu> menus) {
        long distinctMenuSize = menus.stream()
                .distinct()
                .count();
        if (distinctMenuSize != menus.size()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private void validateMenuCategory(List<OrderMenu> menus) {
        long drinkCount = getDrinkCount(menus);
        if (drinkCount == menus.size()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private long getDrinkCount(final List<OrderMenu> menus) {
        return menus.stream()
                .filter(OrderMenu::isDrinkCategory)
                .count();
    }

    private void validateOrderQuantity(List<OrderMenu> menus) {
        int totalQuantity = getTotalQuantity(menus);

        if (totalQuantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private int getTotalQuantity(final List<OrderMenu> menus) {
        return menus.stream()
                .mapToInt(OrderMenu::getQuantity)
                .sum();
    }
}
