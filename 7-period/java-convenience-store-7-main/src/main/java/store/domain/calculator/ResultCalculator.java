package store.domain.calculator;

import java.util.List;
import store.dto.PurchaseStateDto;

public class ResultCalculator {

    private static final int MEMBERSHIP_DISCOUNT_RATE = 30;
    private static final int PERCENT = 100;
    private static final int MEMBERSHIP_MAX_DISCOUNT = 8000;

    private final List<PurchaseStateDto> dtos;

    public ResultCalculator(final List<PurchaseStateDto> dtos) {
        this.dtos = dtos;
    }

    public int calculateTotalQuantity() {
        return dtos.stream()
                .mapToInt(this::getTotalQuantity)
                .sum();
    }

    public int calculateTotalPrice() {
        return dtos.stream()
                .mapToInt(dto -> multiplyPrice(dto.price(), getTotalQuantity(dto)))
                .sum();
    }

    public int calculatePromotionDiscount() {
        return dtos.stream()
                .mapToInt(dto -> multiplyPrice(dto.price(), dto.giftQuantity()))
                .sum();
    }

    public int calculateMembershipDiscount() {
        int total = dtos.stream()
                .mapToInt(dto -> multiplyPrice(dto.price(), dto.regularPurchaseQuantity()))
                .sum();
        int membershipDiscount = total * MEMBERSHIP_DISCOUNT_RATE / PERCENT;
        return Math.min(membershipDiscount, MEMBERSHIP_MAX_DISCOUNT);
    }

    private int multiplyPrice(final int price, final int quantity) {
        return price * quantity;
    }

    private int getTotalQuantity(final PurchaseStateDto dto) {
        return dto.promotionPurchaseQuantity() + dto.regularPurchaseQuantity();
    }
}
