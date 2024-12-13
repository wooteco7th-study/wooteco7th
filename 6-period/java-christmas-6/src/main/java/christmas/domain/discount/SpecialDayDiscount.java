package christmas.domain.discount;

import christmas.domain.OrderMenuGroup;
import christmas.domain.VisitDate;

public class SpecialDayDiscount extends Discount {

    private static final int DISCOUNT = 1_000;

    public SpecialDayDiscount(final DiscountType discountType, final VisitDate visitDate,
                              final OrderMenuGroup orderMenuGroup) {
        super(discountType, visitDate, orderMenuGroup);
    }

    @Override
    public int calculateDiscount() {
        int discount = 0;
        if (canReceiveDiscount() && super.visitDate.isSpecialDay()) {
            discount += DISCOUNT;
        }
        return discount;
    }
}
