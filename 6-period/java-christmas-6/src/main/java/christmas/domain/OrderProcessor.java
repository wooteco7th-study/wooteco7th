package christmas.domain;

import christmas.domain.discount.DiscountProcessor;

import java.util.HashMap;
import java.util.Map;

public class OrderProcessor {

    private static final int MIN_RANGE_TO_GET_DISCOUNT = 10_000;

    private final DiscountProcessor discountProcessor;

    public OrderProcessor(final DiscountProcessor discountProcessor) {
        this.discountProcessor = discountProcessor;
    }

    public Benefit process(final OrderForm orderForm) {
        Map<String, Integer> discountList = new HashMap<>();
        if (canDiscount(orderForm)) {
            discountList = discountProcessor.process(orderForm);
        }
        return new Benefit(discountList);
    }

    private boolean canDiscount(final OrderForm orderForm) {
        return orderForm.getTotalPriceBeforeDiscount() >= MIN_RANGE_TO_GET_DISCOUNT;
    }
}
