package store.payment.dto;

public record PaymentReceipt(
        int totalQuantity,
        int totalPrice,
        int promotionDiscount,
        int membershipDiscount,
        int paymentPrice
) {
}
