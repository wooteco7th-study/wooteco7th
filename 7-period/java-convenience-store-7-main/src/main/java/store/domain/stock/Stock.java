package store.domain.stock;

import store.domain.promotion.Promotion;

public class Stock {

    private final int price;
    private final int quantity;
    private final Promotion promotion;

    public Stock(final int price, final int quantity, final Promotion promotion) {
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public String getPromotionName() {
        if (promotion == null) {
            return "";
        }
        return promotion.getName();
    }
}
