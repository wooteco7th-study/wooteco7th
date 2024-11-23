package christmas.dto;

import christmas.domain.Discount;

public record DiscountReceipt(
        String name,
        int discount
) {
    public static DiscountReceipt of(final Discount discount) {
        return new DiscountReceipt(discount.getDiscountType().getName(), discount.calculateDiscount());
    }
}
