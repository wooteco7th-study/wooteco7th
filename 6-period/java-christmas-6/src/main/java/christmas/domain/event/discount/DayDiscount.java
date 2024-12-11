package christmas.domain.event.discount;

import christmas.domain.Day;
import christmas.domain.Menu.MenuType;
import christmas.domain.order.Orders;
import java.math.BigDecimal;

public class DayDiscount extends Discount {

    private static final int DISCOUNT_AMOUNT = 2_023;
    private static final String WEEKDAYS_DISCOUNT = "평일 할인";
    private static final String WEEKEND_DISCOUNT = "주말 할인";

    public DayDiscount(final Day day, final Orders orders) {
        super(day, orders);
    }

    @Override
    public BigDecimal calculateAmount() {
        if (isApplicable()) {
            if (day.isWeekday()) {
                return BigDecimal.valueOf(DISCOUNT_AMOUNT * orders.countSameTypeMenu(MenuType.DESSERT));
            }
            return BigDecimal.valueOf(DISCOUNT_AMOUNT * orders.countSameTypeMenu(MenuType.MAIN));
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String getName() {
        if (day.isWeekday()) {
            return WEEKDAYS_DISCOUNT;
        }
        return WEEKEND_DISCOUNT;
    }

    @Override
    protected boolean isWithinDeadline() {
        return day.isInDecember();
    }
}
