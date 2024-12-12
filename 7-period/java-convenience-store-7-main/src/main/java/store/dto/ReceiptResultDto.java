package store.dto;

public record ReceiptResultDto(int totalQuantity, int totalPrice, int promotionDiscount, int membershipDiscount,
                               int payPrice) {
}
