package christmas.domain;

public class WeekendDiscount extends Discount {

    private static final int DISCOUNT = 2_023;
    private static final MenuType DISCOUNT_MENU_TYPE = MenuType.MAIN;
    private final VisitDate visitDate;

    public WeekendDiscount(final DiscountType discountType, final OrderGroup orderGroup, final VisitDate visitDate) {
        super(discountType, orderGroup);
        this.visitDate = visitDate;
    }

    @Override
    public boolean canReceiveDiscount() {
        return visitDate.isWeekend() && isExceedsDiscountCondition();
    }

    @Override
    public int calculateDiscount() {
        final int count = countMenuType(DISCOUNT_MENU_TYPE);
        return DISCOUNT * count;
    }
}
