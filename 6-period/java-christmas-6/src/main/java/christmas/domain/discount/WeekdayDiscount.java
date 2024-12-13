package christmas.domain.discount;

import christmas.domain.OrderMenu;
import christmas.domain.OrderMenuGroup;
import christmas.domain.VisitDate;

public class WeekdayDiscount extends Discount {

    private static final int DISCOUNT = 2_023;

    public WeekdayDiscount(final DiscountType discountType, final VisitDate visitDate,
                           final OrderMenuGroup orderMenuGroup) {
        super(discountType, visitDate, orderMenuGroup);
    }

    @Override
    public int calculateDiscount() {
        int discount = 0;
        if (canReceiveDiscount() && super.visitDate.isWeekday()) {
            final long count = orderMenuGroup.getOrderMenus().stream()
                    .filter(OrderMenu::isDessert)
                    .count();
            discount += (int) (count * DISCOUNT);
        }
        return discount;
    }
}
