package store.product.domain;

import store.promotion.Promotion;

public class PromotionProduct extends Product {

    private final Promotion promotion;

    public PromotionProduct(final String name, final ProductType productType, final int quantity, final int price,
                            final Promotion promotion) {
        super(name, productType, quantity, price);
        this.promotion = promotion;
    }



}
