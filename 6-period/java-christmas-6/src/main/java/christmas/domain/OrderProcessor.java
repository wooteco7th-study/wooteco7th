package christmas.domain;

import christmas.domain.discount.DiscountProcessor;

public class OrderProcessor {

    public static final int MIN_RANGE_TO_GET_DISCOUNT = 10000;
    private final DiscountProcessor DiscountProcessor;

    public OrderProcessor(final christmas.domain.discount.DiscountProcessor discountProcessor) {
        DiscountProcessor = discountProcessor;
    }

    public void process(final OrderForm orderForm) {
        boolean canDiscount = canDiscount(orderForm);
    }

    private boolean canDiscount(final OrderForm orderForm) {
        return orderForm.getTotalPriceBeforeDiscount() >= MIN_RANGE_TO_GET_DISCOUNT;
    }
}
