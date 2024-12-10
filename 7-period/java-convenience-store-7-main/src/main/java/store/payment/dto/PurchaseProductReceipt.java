package store.payment.dto;

import store.product.domain.PurchaseProduct;

public record PurchaseProductReceipt(
        String name,
        int totalQuantity,
        int totalPrice
) {
    public static PurchaseProductReceipt of(final PurchaseProduct purchaseProduct) {
        return new PurchaseProductReceipt(
                purchaseProduct.getName(),
                purchaseProduct.calculateTotalQuantity(),
                purchaseProduct.calculateTotalPrice()
        );
    }
}
