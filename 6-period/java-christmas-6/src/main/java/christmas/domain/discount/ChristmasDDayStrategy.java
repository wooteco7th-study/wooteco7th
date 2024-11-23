package christmas.domain.discount;

import christmas.domain.OrderForm;
import christmas.domain.VisitDate;

public class ChristmasDDayStrategy implements DiscountStrategy {

    private static final int START_DATE = 1;
    private static final int END_DATE = 25;
    private static final int START_AMOUNT = 900;
    private static final int INCREASE_AMOUNT = 100;

    @Override
    public boolean isApplicable(final VisitDate visitDate) {
        return visitDate.getDate() >= START_DATE && visitDate.getDate() <= END_DATE;
    }

    @Override
    public int appliedAmount(final OrderForm orderForm) {
        return START_AMOUNT + (orderForm.getOrderDate().getDate() * INCREASE_AMOUNT);
    }
}
