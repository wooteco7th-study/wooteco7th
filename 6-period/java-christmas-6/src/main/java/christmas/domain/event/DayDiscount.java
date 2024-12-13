package christmas.domain.event;

import christmas.domain.date.Day;
import christmas.domain.menu.MenuType;
import christmas.domain.order.Orders;

public class DayDiscount extends Discount {

    private static final int UNIT_DISCOUNT_PRICE = 2023;

    protected DayDiscount(final Day day, final Orders orders) {
        super(day, orders);
    }

    @Override
    protected boolean isPeriodApplicable() {
        return day.isInDecember();
    }

    @Override
    int calculateDiscount() {
        if (!isPeriodApplicable()) {
            return 0;
        }
        if (day.isWeekday()) { // 평일
            return orders.calculateMenuQuantity(MenuType.디저트) * UNIT_DISCOUNT_PRICE;
        }
        return orders.calculateMenuQuantity(MenuType.메인) * UNIT_DISCOUNT_PRICE;
    }
}
