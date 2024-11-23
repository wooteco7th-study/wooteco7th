package christmas.domain;

public class ChristmasDiscount extends Discount {

    private static final int DISCOUNT_DEFAULT = 1_000;
    private static final int DISCOUNT_INCREASE = 100;

    private final VisitDate visitDate;

    public ChristmasDiscount(final DiscountType discountType, final OrderGroup orderGroup, final VisitDate visitDate) {
        super(discountType, orderGroup);
        this.visitDate = visitDate;
    }

    @Override
    public boolean canReceiveDiscount() {
        return visitDate.isBeforeChristmas() && isExceedsDiscountCondition();
    }

    @Override
    public int calculateDiscount() {
        final int dayOfMonth = visitDate.getDayOfMonth();
        return DISCOUNT_DEFAULT + ((dayOfMonth - 1) * DISCOUNT_INCREASE);
    }
}
