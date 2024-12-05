package christmas.domain.discount;

import christmas.domain.OrderForm;
import christmas.domain.VisitDate;

import java.time.LocalDate;

import static christmas.domain.discount.DiscountCategory.SPECIAL;

public class SpecialStrategy implements DiscountStrategy {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int SUNDAY_VALUE = 7;
    public static final int CHRISTMAS_DAY_VALUE = 25;
    private static final int DISCOUNT_AMOUNT = 1000;

    @Override
    public boolean isApplicable(final VisitDate visitDate) {
        LocalDate localDate = LocalDate.of(YEAR, MONTH, visitDate.getDate());
        int value = localDate.getDayOfWeek().getValue();
        return value == SUNDAY_VALUE || visitDate.getDate() == CHRISTMAS_DAY_VALUE;
    }

    @Override
    public int appliedAmount(final OrderForm orderForm) {
        return DISCOUNT_AMOUNT;
    }

    @Override
    public String getName() {
        return SPECIAL.getName();
    }
}
