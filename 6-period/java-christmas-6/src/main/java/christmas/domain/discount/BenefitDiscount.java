package christmas.domain.discount;

import christmas.domain.Menu;
import christmas.domain.OrderMenuGroup;
import christmas.domain.VisitDate;

public class BenefitDiscount extends Discount {

    private static final int DISCOUNT_CONDITION = 120_000;
    private static final Menu BENEFIT = Menu.CHAMPAGNE;
    private static final int BENEFIT_QUANTITY = 1;

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

    public int getBenefitQuantity() {
        return BENEFIT_QUANTITY;
    }
}
