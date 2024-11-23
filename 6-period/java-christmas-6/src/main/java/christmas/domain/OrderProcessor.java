package christmas.domain;

import christmas.domain.discount.DiscountProcessor;
import christmas.domain.discount.DiscountStrategy;

import java.util.HashMap;
import java.util.Map;

public class OrderProcessor {

    private static final int MIN_RANGE_TO_GET_DISCOUNT = 10_000;
    private static final int MIN_RAGE_TO_GET_FREE_GIFT = 120_000;

    private final DiscountProcessor discountProcessor;

    public OrderProcessor(final DiscountProcessor discountProcessor) {
        this.discountProcessor = discountProcessor;
    }

    public Benefit process(final OrderForm orderForm) {
        int freeAmount = 0;
        Map<DiscountStrategy, Integer> discountList = new HashMap<>();
        if (canDiscount(orderForm)) {
            discountList = discountProcessor.process(orderForm);
        }
        if (canGetFreeGift(orderForm)) {
            freeAmount = 25_000;
        }
        return new Benefit(discountList, freeAmount);
    }

    private boolean canDiscount(final OrderForm orderForm) {
        return orderForm.getTotalPriceBeforeDiscount() >= MIN_RANGE_TO_GET_DISCOUNT;
    }

    private boolean canGetFreeGift(final OrderForm orderForm) {
        return orderForm.getTotalPriceBeforeDiscount() >= MIN_RAGE_TO_GET_FREE_GIFT;
    }
}
