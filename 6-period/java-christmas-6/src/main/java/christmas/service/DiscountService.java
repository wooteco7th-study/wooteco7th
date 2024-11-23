package christmas.service;

import christmas.domain.Badge;
import christmas.domain.DiscountInfo;
import christmas.domain.Promotion;
import christmas.domain.vo.OrderMenus;
import christmas.domain.vo.VisitDate;

public class DiscountService {


    public DiscountInfo discount(OrderMenus orderMenus, VisitDate visitDate) {
        DiscountInfo discountInfo = applyDiscount(orderMenus, visitDate);

        discountInfo = updatePromotion(discountInfo);
        discountInfo = updateBadge(discountInfo);

        return discountInfo;
    }

    private static DiscountInfo updateBadge(DiscountInfo discountInfo) {
        int totalDiscountAmount = discountInfo.getTotalDiscountAmount();
        discountInfo = discountInfo.updateBadge(Badge.of(totalDiscountAmount));
        return discountInfo;
    }

    private static DiscountInfo updatePromotion(DiscountInfo discountInfo) {
        int priceBeforeDiscount = discountInfo.getPriceBeforeDiscount();
        discountInfo = discountInfo.updatePromotion(Promotion.of(priceBeforeDiscount));
        return discountInfo;
    }

    private static DiscountInfo applyDiscount(final OrderMenus orderMenus, final VisitDate visitDate) {
        DiscountInfo discountInfo = DiscountInfo.initialize(orderMenus, visitDate);
        discountInfo = orderMenus.discount(discountInfo, visitDate);
        return discountInfo;
    }

}
