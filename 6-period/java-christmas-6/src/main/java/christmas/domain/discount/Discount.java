package christmas.domain.discount;

import christmas.domain.order.MenuType;
import christmas.domain.order.OrderGroup;

public abstract class Discount {

    private static final int DISCOUNT_CONDITION = 10_000;
    private static final int BENEFIT_EVENT_CONDITION = 120_000;
    private final DiscountType discountType;
    private final OrderGroup orderGroup;

    protected Discount(final DiscountType discountType, final OrderGroup orderGroup) {
        this.discountType = discountType;
        this.orderGroup = orderGroup;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public abstract boolean canReceiveDiscount();

    public abstract int calculateDiscount();

    protected boolean isExceedsDiscountCondition() {
        return orderGroup.calculateOrdersTotalPrice() >= DISCOUNT_CONDITION;
    }

    protected boolean isExceedsBenefitEventCondition() {
        return orderGroup.calculateOrdersTotalPrice() >= BENEFIT_EVENT_CONDITION;
    }

    protected int countMenuType(final MenuType menuType) {
        return orderGroup.countMenuType(menuType);
    }

}
