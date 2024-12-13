package christmas.domain.discount;

import christmas.domain.OrderMenuGroup;
import christmas.domain.VisitDate;

public class ChristmasDiscount extends Discount{

    private static final int DEFAULT_DISCOUNT = 1_000;
    private static final int INCREASE_DISCOUNT = 100;

    public ChristmasDiscount(final DiscountType discountType, final VisitDate visitDate,
                             final OrderMenuGroup orderMenuGroup) {
        super(discountType, visitDate, orderMenuGroup);
    }

    @Override
    public int calculateDiscount() {
        int discount = 0;
        if (canReceiveDiscount() && super.visitDate.isBeforeChristmas()) {
            discount += ((visitDate.getDayOfMonth() - 1) * INCREASE_DISCOUNT) + DEFAULT_DISCOUNT;
        }
        return discount;
    }
}
