package christmas.domain;

import christmas.domain.discount.DiscountStrategy;

import java.util.Map;

public record Benefit(
        Map<DiscountStrategy, Integer> discountList,
        int freeGiftAmount
) {
}
