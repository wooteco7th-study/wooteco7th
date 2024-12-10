package store.domain.promotion;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import store.domain.order.Order;
import store.domain.stock.Stocks;
import store.dto.ResultDto;

public class PromotionProcessor {

    // - `오늘 날짜`가 `프로모션 기간 내`에 포함되었는지 확인
    //- 프로모션 적용이 가능한 상품에 대해 **고객이 해당 수량보다 적게 가져온 경우**, 필요한 수량을 **추가로 가져오면 혜택을 받을 수 있음을 안내**
    //- **프로모션 재고가 부족**하여 **일부 수량을 프로모션 혜택 없이 결제**해야 하는 경우, **일부 수량에 대해 정가로 결제하게 됨을 안내**

    private final Stocks stocks;

    public PromotionProcessor(final Stocks stocks) {
        this.stocks = stocks;
    }

    public boolean isPromotionPeriod() {
        LocalDate now = DateTimes.now().toLocalDate();
        return stocks.isPromotionPeriod(now);
    }

    public ResultDto process(final Order order) {
        if (!isPromotionPeriod()) {
            return regularPurchase(order);
        }
        return processWithPromotion(order);
    }

    private ResultDto processWithPromotion(final Order order) {
        if (hasPromotionStockInsufficient(order)) {
            return guideMixedPurchase(order);
        }
        if (canGift(order)) {
            return guideGift(order);
        }
        return processOnlyPromotionPurchase(order);
    }

    private ResultDto processOnlyPromotionPurchase(final Order order) {
        int purchaseQuantity = order.getPurchaseQuantity();
        int unitQuantity = stocks.getPromotionUnitQuantity();
        int numberOfUnit = divideByUnitQuantity(purchaseQuantity, unitQuantity);
        int membershipApplyCount = purchaseQuantity - (numberOfUnit * unitQuantity);
        int giftCount = stocks.calculateGiftQuantity(numberOfUnit);
        stocks.subtractPromotionStock(purchaseQuantity);
        return ResultDto.of(ProcessType.ONLY_PROMOTION, order, purchaseQuantity, 0, giftCount, stocks,
                membershipApplyCount);
    }

    private ResultDto guideGift(final Order order) {
        int numberOfUnit = divideByUnitQuantity(order.getPurchaseQuantity(), stocks.getPromotionUnitQuantity());
        return ResultDto.of(ProcessType.CAN_GIFT, order, order.getPurchaseQuantity(), 0,
                stocks.calculateGiftQuantity(numberOfUnit), stocks, 0);
    }

    private boolean canGift(final Order order) {
        int purchaseQuantity = order.getPurchaseQuantity();
        int unitQuantity = stocks.getPromotionUnitQuantity();
        return purchaseQuantity % unitQuantity == stocks.getBuyQuantity()
                && stocks.calculateRemainingIsMoreThanEqualToBuyQuantity(purchaseQuantity);
    }

    // - 프로모션 재고가 7개이고 구매 상품 수가 10개일때 (6개(2+1)+(2+1))는 프로모션이 적용되지만 나머지 4개는 적용되지 않는다.
    //- n개 구매, 프로모션 재고 s개 : `n - (s / (a+b) * (a+b))` 개는 프로모션 적용 X, `s / (a+b) * (a+b)`는 프로모션 적용
    private ResultDto guideMixedPurchase(final Order order) {
        int unitQuantity = stocks.getPromotionUnitQuantity();
        int promotionQuantity = stocks.getPromotionStockQuantity();
        int numberOfUnit = divideByUnitQuantity(promotionQuantity, unitQuantity);
        int promotionPurchaseQuantity = numberOfUnit * unitQuantity;
        int regularPurchaseQuantity = order.getPurchaseQuantity() - promotionPurchaseQuantity;
        int giftQuantity = stocks.calculateGiftQuantity(numberOfUnit);
        return ResultDto.of(ProcessType.MIXED, order, promotionPurchaseQuantity, regularPurchaseQuantity, giftQuantity,
                stocks, regularPurchaseQuantity);
    }

    public int divideByUnitQuantity(int quantity, int unitQuantity) {
        return quantity / unitQuantity;
    }

    private boolean hasPromotionStockInsufficient(final Order order) {
        return !stocks.hasPromotionEnoughQuantity(order.getPurchaseQuantity());
    }

    private ResultDto regularPurchase(final Order order) {
        int purchaseQuantity = order.getPurchaseQuantity();
        stocks.subtractRegularStock(purchaseQuantity);
        return ResultDto.of(ProcessType.REGULAR, order, 0, purchaseQuantity, 0, stocks, purchaseQuantity);
    }
}
