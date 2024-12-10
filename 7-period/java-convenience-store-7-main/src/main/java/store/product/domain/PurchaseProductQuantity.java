package store.product.domain;

public class PurchaseProductQuantity {

    private int promotionQuantity;
    private int nonPromotionQuantity;
    private int promotionAndNonPromotionQuantity;

    public PurchaseProductQuantity(final int promotionQuantity, final int nonPromotionQuantity,
                                   final int promotionAndNonPromotionQuantity) {
        this.promotionQuantity = promotionQuantity;
        this.nonPromotionQuantity = nonPromotionQuantity;
        this.promotionAndNonPromotionQuantity = promotionAndNonPromotionQuantity;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public int getNonPromotionQuantity() {
        return nonPromotionQuantity;
    }

    public int getPromotionAndNonPromotionQuantity() {
        return promotionAndNonPromotionQuantity;
    }

    public void updatePromotionQuantity(final int quantity) {
        this.promotionQuantity = quantity;
    }

    public void updateNonPromotionQuantity(final int quantity) {
        this.nonPromotionQuantity = quantity;
    }

    public void updatePromotionAndNonPromotionQuantity(final int quantity) {
        this.promotionAndNonPromotionQuantity = quantity;
    }
}
