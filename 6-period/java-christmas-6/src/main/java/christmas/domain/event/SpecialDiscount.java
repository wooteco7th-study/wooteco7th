package christmas.domain.event;

import christmas.domain.date.Day;
import christmas.domain.order.Orders;

public class SpecialDiscount extends Discount {

    private static final int SPECIAL_DISCOUNT = 1000;

    protected SpecialDiscount(final Day day, final Orders orders) {
        super(day, orders);
    }

    @Override
    protected boolean isPeriodApplicable() {
        return day.isSpecialDay();
    }

    @Override
    int calculateDiscount() {
        if (isPeriodApplicable()) {
            return SPECIAL_DISCOUNT;
        }
        return 0;
    }
}
