package christmas.domain;

public class SpecialDiscount extends Discount {

    private static final int DISCOUNT = 1_000;
    private final VisitDate visitDate;

    public SpecialDiscount(final DiscountType discountType, final OrderGroup orderGroup, final VisitDate visitDate) {
        super(discountType, orderGroup);
        this.visitDate = visitDate;
    }

    @Override
    public boolean canReceiveDiscount() {
        return visitDate.isSpecialDay() && isExceedsDiscountCondition();
    }

    @Override
    public int calculateDiscount() {
        return DISCOUNT;
    }
}
