package store.domain.stock;

import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionType;
import store.exception.CustomIllegalArgumentException;
import store.exception.ErrorMessage;

public class Stocks {

    private static final String EMPTY_STRING = "";

    private final int price;
    private int promotionQuantity;
    private int regularQuantity;
    private Promotion promotion;
    private PromotionType promotionType;

    public Stocks(final int price, final int promotionQuantity, final int regularQuantity, final Promotion promotion,
                  final PromotionType promotionType) {
        this.price = price;
        this.promotionQuantity = promotionQuantity;
        this.regularQuantity = regularQuantity;
        this.promotion = promotion;
        this.promotionType = promotionType;
    }

    public static Stocks from(final int price, final int quantity, final Promotion promotion) {
        if (promotion == null) {
            return new Stocks(price, 0, quantity, null, PromotionType.NONE);
        }
        return new Stocks(price, quantity, 0, promotion, PromotionType.EXIST);
    }

    public void add(final int quantity, final Promotion inputPromotion) {
        if (inputPromotion == null) {
            validateQuantity(regularQuantity);
            regularQuantity = quantity;
            return;
        }
        validateQuantity(promotionQuantity);
        promotionQuantity = quantity;
        promotion = inputPromotion;
        promotionType = PromotionType.EXIST;
    }

    private void validateQuantity(final int quantity) {
        if (quantity > 0) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_FILE_FORMAT);
        }
    }

    public boolean hasPromotion() {
        return promotionType.hasPromotion();
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity(Stocks stocks, boolean hasPromotion) {
        if (hasPromotion) {
            return stocks.getPromotionQuantity();
        }
        return stocks.getRegularQuantity();
    }

    public String getPromotionName(boolean hasPromotion) {
        if (hasPromotion) {
            return promotion.getName();
        }
        return EMPTY_STRING;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public int getRegularQuantity() {
        return regularQuantity;
    }

    public boolean hasEnoughQuantity(final int purchaseQuantity) {
        return promotionQuantity + regularQuantity >= purchaseQuantity;
    }
}
