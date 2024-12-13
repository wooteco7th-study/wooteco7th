package christmas.domain.event;

import christmas.domain.date.Day;
import christmas.domain.order.Orders;

public class ChristmasDdayDiscount extends Discount {

    private static final int DISCOUNT_START_PRICE = 1000;
    private static final int UNIT_PRICE = 100;

    public ChristmasDdayDiscount(final Day day, final Orders orders) {
        super(day, orders);
    }

    @Override
    protected boolean isPeriodApplicable() {
        return day.isWithinChristmas();
    }

    @Override
    int calculateDiscount() {
        if (isPeriodApplicable()) {
            return DISCOUNT_START_PRICE + day.calculateIntervalFromBeginning() * UNIT_PRICE;
        }
        return 0;
    }
}
