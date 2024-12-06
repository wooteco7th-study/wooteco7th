package christmas.domain.discount;

import christmas.domain.order.MenuType;
import christmas.domain.order.OrderGroup;
import christmas.domain.visit.VisitDate;

public class WeekdayDiscount extends Discount {

    private static final int DISCOUNT = 2_023;
    private static final MenuType DISCOUNT_MENU_TYPE = MenuType.DESSERT;
    private final VisitDate visitDate;

    public WeekdayDiscount(final DiscountType discountType, final OrderGroup orderGroup, final VisitDate visitDate) {
        super(discountType, orderGroup);
        this.visitDate = visitDate;
    }

    @Override
    public boolean canReceiveDiscount() {
        return visitDate.isWeekday() && isExceedsDiscountCondition();
    }

    @Override
    public int calculateDiscount() {
        final int count = countMenuType(DISCOUNT_MENU_TYPE);
        return DISCOUNT * count;
    }
}
