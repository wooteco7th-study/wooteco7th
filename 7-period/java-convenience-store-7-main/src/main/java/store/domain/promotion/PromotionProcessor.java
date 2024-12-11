package store.domain.promotion;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import store.domain.order.Order;
import store.domain.stock.Stocks;
import store.dto.PurchaseStateDto;

public class PromotionProcessor {

    private final Stocks stocks;

    public PromotionProcessor(final Stocks stocks) {
        this.stocks = stocks;
    }

    public boolean isPromotionPeriod() {
        LocalDate now = DateTimes.now().toLocalDate();
        return stocks.isPromotionPeriod(now);
    }

    public PurchaseStateDto process(final Order order) {
        if (!isPromotionPeriod()) {
            return regularPurchase(order);
        }
        return processWithPromotion(order);
    }

    private PurchaseStateDto processWithPromotion(final Order order) {
        if (hasPromotionStockInsufficient(order)) {
            return guideMixedPurchase(order);
        }
        if (canGift(order)) {
            return guideGift(order);
        }
        return processOnlyPromotionPurchase(order);
    }

    private PurchaseStateDto processOnlyPromotionPurchase(final Order order) {
        int purchaseQuantity = order.getPurchaseQuantity();
        int unitQuantity = stocks.getPromotionUnitQuantity();
        int numberOfUnit = divideByUnitQuantity(purchaseQuantity, unitQuantity);
        int promotionApplyCount = divideByUnitQuantity(purchaseQuantity, unitQuantity) * unitQuantity;
        int regularPurchaseQuantity = purchaseQuantity - promotionApplyCount;
        int giftCount = stocks.calculateGiftQuantity(numberOfUnit);
        stocks.subtractPromotionStock(purchaseQuantity);
        return PurchaseStateDto.of(ProcessType.ONLY_PROMOTION, order, promotionApplyCount, regularPurchaseQuantity, giftCount, stocks
        );
    }

    private PurchaseStateDto guideGift(final Order order) {
        int numberOfUnit = divideByUnitQuantity(order.getPurchaseQuantity(), stocks.getPromotionUnitQuantity());
        return PurchaseStateDto.of(ProcessType.CAN_GIFT, order, order.getPurchaseQuantity(), 0,
                stocks.calculateGiftQuantity(numberOfUnit), stocks);
    }

    private boolean canGift(final Order order) {
        int purchaseQuantity = order.getPurchaseQuantity();
        int unitQuantity = stocks.getPromotionUnitQuantity();
        return purchaseQuantity % unitQuantity == stocks.getBuyQuantity()
                && stocks.calculateRemainingIsMoreThanEqualToBuyQuantity(purchaseQuantity);
    }

    private PurchaseStateDto guideMixedPurchase(final Order order) {
        int unitQuantity = stocks.getPromotionUnitQuantity();
        int promotionQuantity = stocks.getPromotionStockQuantity();
        int numberOfUnit = divideByUnitQuantity(promotionQuantity, unitQuantity);
        int promotionPurchaseQuantity = numberOfUnit * unitQuantity;
        int regularPurchaseQuantity = order.getPurchaseQuantity() - promotionPurchaseQuantity;
        int giftQuantity = stocks.calculateGiftQuantity(numberOfUnit);
        return PurchaseStateDto.of(ProcessType.MIXED, order, promotionPurchaseQuantity, regularPurchaseQuantity, giftQuantity,
                stocks);
    }

    public int divideByUnitQuantity(int quantity, int unitQuantity) {
        return quantity / unitQuantity;
    }

    private boolean hasPromotionStockInsufficient(final Order order) {
        return !stocks.hasPromotionEnoughQuantity(order.getPurchaseQuantity());
    }

    private PurchaseStateDto regularPurchase(final Order order) {
        int purchaseQuantity = order.getPurchaseQuantity();
        stocks.subtractRegularStock(purchaseQuantity);
        return PurchaseStateDto.of(ProcessType.REGULAR, order, 0, purchaseQuantity, 0, stocks);
    }
}
