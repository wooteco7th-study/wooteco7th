package store.domain.stock;

import java.time.LocalDate;
import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionType;
import store.exception.CustomIllegalArgumentException;
import store.exception.ErrorMessage;

public class Stocks {

    private static final String EMPTY_STRING = "";

    private final int price;
    private int promotionStockQuantity;
    private int regularStockQuantity;
    private Promotion promotion;
    private PromotionType promotionType;

    public Stocks(final int price, final int promotionStockQuantity, final int regularStockQuantity,
                  final Promotion promotion,
                  final PromotionType promotionType) {
        this.price = price;
        this.promotionStockQuantity = promotionStockQuantity;
        this.regularStockQuantity = regularStockQuantity;
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
            addRegularStock(quantity);
            return;
        }
        addPromotionStock(quantity, inputPromotion);
        promotionType = PromotionType.EXIST;
    }

    private void addRegularStock(final int quantity) {
        validateQuantity(regularStockQuantity);
        regularStockQuantity = quantity;
    }

    private void addPromotionStock(final int quantity, final Promotion inputPromotion) {
        validateQuantity(promotionStockQuantity);
        promotionStockQuantity = quantity;
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
            return stocks.getPromotionStockQuantity();
        }
        return stocks.getRegularStockQuantity();
    }

    public String getPromotionName(boolean hasPromotion) {
        if (hasPromotion) {
            return promotion.getName();
        }
        return EMPTY_STRING;
    }

    public int getPromotionUnitQuantity() {
        return promotion.getUnitQuantity();
    }

    public int getBuyQuantity() {
        return promotion.getBuyQuantity();
    }

    public int getPromotionStockQuantity() {
        return promotionStockQuantity;
    }

    public int getRegularStockQuantity() {
        return regularStockQuantity;
    }

    public boolean hasTotalEnoughQuantity(final int purchaseQuantity) {
        return promotionStockQuantity + regularStockQuantity >= purchaseQuantity;
    }

    public boolean hasPromotionEnoughQuantity(final int purchaseQuantity) {
        return promotionStockQuantity >= purchaseQuantity;
    }

    public boolean isPromotionPeriod(final LocalDate now) {
        if (promotionType == PromotionType.EXIST) {
            return promotion.isPromotionPeriod(now);
        }
        return false;
    }

    public void subtractRegularStock(final int purchaseQuantity) {
        if (regularStockQuantity < purchaseQuantity) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_QUANTITY_OUT_OF_STOCK);
        }
        regularStockQuantity -= purchaseQuantity;
    }

    public int calculateGiftQuantity(final int numberOfUnit) {
        return promotion.calculateGiftQuantity(numberOfUnit);
    }

    public boolean calculateRemainingIsMoreThanEqualToBuyQuantity(final int purchaseQuantity) {
        return getPromotionStockQuantity() - purchaseQuantity >= getBuyQuantity();
    }

    public void subtractPromotionStock(final int purchaseQuantity) {
        promotionStockQuantity -= purchaseQuantity;
    }
}
