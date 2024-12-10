package store.dto;

import store.domain.order.Order;
import store.domain.promotion.ProcessType;
import store.domain.stock.Stocks;

// - 상품명
//- 프로모션 적용 수량
//- 일반 재고 적용 수량
//- 보너스 수량
//- 금액
public record ResultDto(ProcessType type, String productName, int promotionPurchaseQuantity,
                        int regularPurchaseQuantity, int giftQuantity, int price, int membershipApplyCount) {

    public static ResultDto of(final ProcessType type, final Order order, int promotionPurchaseQuantity,
                               int regularPurchaseQuantity, int giftQuantity, Stocks stocks, int membershipApplyCount) {
        return new ResultDto(type, order.getName(), promotionPurchaseQuantity, regularPurchaseQuantity, giftQuantity,
                stocks.getPrice(), membershipApplyCount);
    }
}
