package store.product.domain;


public class PurchaseProduct {

    private final String name;
    private final int price;
    private final String promotionName;
    private final PurchaseProductQuantity purchaseProductQuantity;

    public PurchaseProduct(final String name, final int price, final String promotionName,
                           final PurchaseProductQuantity purchaseProductQuantity) {
        this.name = name;
        this.price = price;
        this.promotionName = promotionName;
        this.purchaseProductQuantity = purchaseProductQuantity;
    }

    public PurchaseProductQuantity getPurchaseProductQuantity() {
        return purchaseProductQuantity;
    }

    public int getPrice() {
        return price;
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

    public int calculateNonPromotionPrice() {
        return price * (purchaseProductQuantity.getNonPromotionQuantity()
                + purchaseProductQuantity.getPromotionAndNonPromotionQuantity());
    }

    public int calculatePromotionPrice() {
        return price * purchaseProductQuantity.getPromotionQuantity();
    }

    public int calculateTotalPrice() {
        return calculateTotalQuantity() * price;
    }

    public int calculateTotalQuantity() {
        return purchaseProductQuantity.getPromotionQuantity() + purchaseProductQuantity.getNonPromotionQuantity()
                + purchaseProductQuantity.getPromotionAndNonPromotionQuantity();
    }
}
