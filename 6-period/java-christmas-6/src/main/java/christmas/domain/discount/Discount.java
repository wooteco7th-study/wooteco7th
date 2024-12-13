package christmas.domain.discount;

import christmas.domain.OrderMenuGroup;
import christmas.domain.VisitDate;

public abstract class Discount {

    private static final int DISCOUNT_CONDITION = 10_000;

    protected final DiscountType discountType;
    protected final VisitDate visitDate;
    protected final OrderMenuGroup orderMenuGroup;

    protected Discount(final DiscountType discountType, final VisitDate visitDate, final OrderMenuGroup orderMenuGroup) {
        this.discountType = discountType;
        this.visitDate = visitDate;
        this.orderMenuGroup = orderMenuGroup;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public abstract int calculateDiscount();

    protected boolean canReceiveDiscount() {
        return orderMenuGroup.calculateTotalPrice() >= DISCOUNT_CONDITION;
    }

}
