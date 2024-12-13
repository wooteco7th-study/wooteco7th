package christmas.dto;

import christmas.domain.discount.Discount;
import java.util.List;

public record DiscountReceiptGroup(
        List<DiscountReceipt> discountReceipts
) {

    public static DiscountReceiptGroup of(final List<Discount> discounts) {
        return new DiscountReceiptGroup(discounts.stream()
                .map(DiscountReceipt::of)
                .toList());
    }

    public record DiscountReceipt(
            String name,
            int discount
    ) {
        public static DiscountReceipt of(final Discount discount) {
            return new DiscountReceipt(
                    discount.getDiscountType().getName(),
                    discount.calculateDiscount()
            );
        }
    }
}
