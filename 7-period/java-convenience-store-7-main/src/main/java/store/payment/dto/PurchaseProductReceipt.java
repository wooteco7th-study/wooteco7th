package store.payment.dto;

import store.product.domain.PurchaseProduct;

public record PurchaseProductReceipt(
        String name,
        int totalQuantity
) {
    public static PurchaseProductReceipt of(final PurchaseProduct purchaseProduct) {
        return new PurchaseProductReceipt(
                purchaseProduct.getName(),
                purchaseProduct.calculateTotalQuantity()
        );
    }
}
