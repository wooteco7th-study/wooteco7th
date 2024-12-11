package christmas.domain.discount;

import christmas.domain.OrderForm;
import christmas.domain.VisitDate;

import static christmas.domain.discount.DiscountCategory.FREE_GIFT;

public class FreeGiftStrategy implements DiscountStrategy {

    private static final int MIN_RAGE_TO_GET_FREE_GIFT = 120_000;
    public static final int FREE_GIFT_PRICE = 25_000;


    @Override
    public boolean isApplicable(final VisitDate visitDate) {
        return true;
    }

    @Override
    public int appliedAmount(final OrderForm orderForm) {
        if (orderForm.getTotalPriceBeforeDiscount() >= MIN_RAGE_TO_GET_FREE_GIFT) {
            return FREE_GIFT_PRICE;
        }
        return 0;
    }

    @Override
    public String getName() {
        return FREE_GIFT.getName();
    }
}
