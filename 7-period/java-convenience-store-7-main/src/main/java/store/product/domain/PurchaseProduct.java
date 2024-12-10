package store.product.domain;


public class PurchaseProduct {

    private final String name;
    private final String promotionName;
    private final PurchaseProductQuantity purchaseProductQuantity;

    public PurchaseProduct(final String name, final String promotionName, final PurchaseProductQuantity purchaseProductQuantity) {
        this.name = name;
        this.promotionName = promotionName;
        this.purchaseProductQuantity = purchaseProductQuantity;
    }

    public PurchaseProductQuantity getPurchaseProductQuantity() {
        return purchaseProductQuantity;
    }

    public String getName() {
        return name;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public boolean hasPromotion() {
        return !promotionName.isEmpty();
    }
}
