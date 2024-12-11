package christmas.domain;

import java.util.Map;

import static christmas.domain.discount.DiscountCategory.FREE_GIFT;
import static christmas.domain.discount.FreeGiftStrategy.FREE_GIFT_PRICE;

public record Benefit(
        Map<String, Integer> discountList
) {

    public boolean hasFreeGift() {
        return discountList.containsKey(FREE_GIFT.getName());
    }

    public int getTotalBenefitAmount() {
        return discountList.values()
                .stream()
                .mapToInt(value -> value)
                .sum();
    }

    public int getBenefitDiscount() {
        if (hasFreeGift()) {
            return getTotalBenefitAmount() - FREE_GIFT_PRICE;
        }
        return getTotalBenefitAmount();
    }
}
