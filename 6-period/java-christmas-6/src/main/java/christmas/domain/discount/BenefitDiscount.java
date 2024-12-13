package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.OrderMenuGroup;
import christmas.domain.VisitDate;

public class BenefitDiscount extends Discount {

    private static final int DISCOUNT_CONDITION = 120_000;
    private static final Menu BENEFIT = Menu.CHAMPAGNE;

    public BenefitDiscount(final DiscountType discountType, final VisitDate visitDate,
                           final OrderMenuGroup orderMenuGroup) {
        super(discountType, visitDate, orderMenuGroup);
    }

    @Override
    public int calculateDiscount() {
        int discount = 0;
        if (canReceiveDiscount() && orderMenuGroup.calculateTotalPrice() >= DISCOUNT_CONDITION) {
            discount += BENEFIT.getPrice();
        }
        return discount;
    }

    public Menu getBenefit() {
        return BENEFIT;
    }
}
