package christmas.dto;

import christmas.domain.discount.DiscountType;
import java.util.Map.Entry;

public record DiscountReceipt(
        String name,
        int discount
) {
    public static DiscountReceipt of(final Entry<DiscountType, Integer> entry) {
        final DiscountType discountType = entry.getKey();
        final Integer discount = entry.getValue();
        return new DiscountReceipt(discountType.getName(), discount);
    }
}
