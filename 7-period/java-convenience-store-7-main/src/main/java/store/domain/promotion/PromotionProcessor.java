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
            // 정가 결제
            return regularPurchase(order);
        }
        // 프로모션 적용 -> 프로모션 재고 우선 차감
        // 프로모션 재고가 부족**하여 **일부 수량을 프로모션 혜택 없이 결제**해야 하는 경우, **일부 수량에 대해 정가로 결제하게 됨을 안내**
        if (hasPromotionStockInsufficient(order)) {
            return guideMixedPurchase(order);
        }
        // 프로모션 적용이 가능한 상품에 대해 **고객이 해당 수량보다 적게 가져온 경우**, 필요한 수량을 **추가로 가져오면 혜택을 받을 수 있음을 안내**
        // n개 구매 a+b 프로모션 : n % (a+b) == a 일 경우 1개를 증정받을 수 있다고 안내
        if (canGift(order)) {
            return guideGift(order);
        }
        // 프로모션 결제
        return processOnlyPromotionPurchase(order);
    }

    private ResultDto processOnlyPromotionPurchase(final Order order) {
        int purchaseQuantity = order.getPurchaseQuantity();
        int unitQuantity = stocks.getPromotionUnitQuantity();
        // 프로모션 적용 상품 개수
        int membershipApplyCount = purchaseQuantity - (purchaseQuantity / unitQuantity * unitQuantity);
        // 증정 상품 개수
        int giftCount = stocks.calculateGiftQuantity(unitQuantity);
        stocks.subtractPromotionStock(purchaseQuantity);
        return ResultDto.of(ProcessType.ONLY_PROMOTION, order, purchaseQuantity, 0, giftCount, stocks,
                membershipApplyCount);
    }

    private ResultDto guideGift(final Order order) {
        int numberOfUnit = calculateUnit(order);
        return ResultDto.of(ProcessType.CAN_GIFT, order, order.getPurchaseQuantity(), 0,
                stocks.calculateGiftQuantity(numberOfUnit), stocks, 0);
    }

    private int calculateUnit(final Order order) {
        return order.getPurchaseQuantity() / stocks.getPromotionUnitQuantity();
    }

    private boolean canGift(final Order order) {
        int purchaseQuantity = order.getPurchaseQuantity();
        int unitQuantity = stocks.getPromotionUnitQuantity();
        return purchaseQuantity % unitQuantity == stocks.getBuyQuantity() && stocks.isPromotionHasMoreThanEqualToBuyQuantity();
    }

    // - 프로모션 재고가 7개이고 구매 상품 수가 10개일때 (6개(2+1)+(2+1))는 프로모션이 적용되지만 나머지 4개는 적용되지 않는다.
    //- n개 구매, 프로모션 재고 s개 : `n - (s / (a+b) * (a+b))` 개는 프로모션 적용 X, `s / (a+b) * (a+b)`는 프로모션 적용
    private ResultDto guideMixedPurchase(final Order order) {
        int purchaseQuantity = order.getPurchaseQuantity();
        int unitQuantity = stocks.getPromotionUnitQuantity();
        int promotionQuantity = stocks.getPromotionStockQuantity();
        int promotionPurchaseQuantity = promotionQuantity / unitQuantity * unitQuantity;
        int regularPurchaseQuantity = purchaseQuantity - promotionPurchaseQuantity;
        int giftQuantity = stocks.calculateGiftQuantity(unitQuantity);
        return ResultDto.of(ProcessType.MIXED, order, promotionPurchaseQuantity, regularPurchaseQuantity, giftQuantity,
                stocks, regularPurchaseQuantity);
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
