package christmas.domain;

public abstract class Discount {

    private static final int DISCOUNT_CONDITION = 10_000;
    private final DiscountType discountType;

    protected Discount(final DiscountType discountType) {
        this.discountType = discountType;
    }

    public String getDiscountTypeName() {
        return discountType.getName();
    }

    public abstract boolean canReceiveDiscount();

    public abstract int calculateDiscount();

    protected boolean isExceedsDiscountCondition(final long orderPrice) {
        return orderPrice >= DISCOUNT_CONDITION;
    }

}
