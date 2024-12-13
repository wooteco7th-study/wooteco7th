package christmas.domain.event;

import christmas.domain.date.Day;
import christmas.domain.order.Orders;

public abstract class Discount {

    private static final int MIN_EVENT_APPLICABLE_ORDER_PRICE = 10_000;

    protected final Day day;
    protected final Orders orders;

    protected Discount(final Day day, final Orders orders) {
        this.day = day;
        this.orders = orders;
    }

    protected boolean isApplicable() {
        return isPeriodApplicable() && exceedMinimumOrderPrice();
    }

    private boolean exceedMinimumOrderPrice() {
        return orders.calculateTotalPrice() >= MIN_EVENT_APPLICABLE_ORDER_PRICE;
    }

    protected abstract boolean isPeriodApplicable();

    abstract int calculateDiscount();
}
