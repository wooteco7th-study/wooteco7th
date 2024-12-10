package store.product.domain;

import java.util.Collections;
import java.util.List;

public class PurchaseProductGroup {

    private final List<PurchaseProduct> purchaseProducts;

    public PurchaseProductGroup(final List<PurchaseProduct> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }

    public List<PurchaseProduct> getPurchaseProducts() {
        return Collections.unmodifiableList(purchaseProducts);
    }

    public int calculateNonPromotionTotalPrice() {
        return purchaseProducts.stream()
                .mapToInt(PurchaseProduct::calculateNonPromotionPrice)
                .sum();
    }

    public int calculateTotalPrice() {
        return purchaseProducts.stream()
                .mapToInt(PurchaseProduct::calculateTotalPrice)
                .sum();
    }
}
