package store.payment.dto;

public record PaymentReceipt(
        int totalPrice,
        int promotionDiscount,
        int membershipDiscount,
        int paymentPrice
) {
}
