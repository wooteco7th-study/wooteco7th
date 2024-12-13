package christmas.domain.discount;

import christmas.domain.OrderMenu;
import christmas.domain.OrderMenuGroup;
import christmas.domain.VisitDate;

public class WeekendDiscount extends Discount {

    private static final int DISCOUNT = 2_023;

    public WeekendDiscount(final DiscountType discountType, final VisitDate visitDate,
                           final OrderMenuGroup orderMenuGroup) {
        super(discountType, visitDate, orderMenuGroup);
    }

    @Override
    public int calculateDiscount() {
        int discount = 0;
        if (canReceiveDiscount() && super.visitDate.isWeekend()) {
            final long count = super.orderMenuGroup.getOrderMenus().stream()
                    .filter(OrderMenu::isMain)
                    .count();
            discount += (int) (DISCOUNT * count);
        }
        return discount;
    }
}
