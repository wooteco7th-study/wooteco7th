package christmas.domain;


import christmas.domain.vo.MyBadge;
import christmas.domain.vo.MyDiscountEvent;
import christmas.domain.vo.MyPromotion;
import christmas.domain.vo.OrderMenus;
import christmas.domain.vo.VisitDate;

public class Discounting {
    private final OrderMenus orderMenus;
    private final VisitDate visitDate;
    private final int priceBeforeDiscount;
    private final MyDiscountEvent myDiscountEvent;
    private final MyPromotion myPromotion;
    private final int totalDiscountAmount;
    private final MyBadge myBadge;

    public Discounting(final OrderMenus orderMenus, final VisitDate visitDate, final int priceBeforeDiscount,
                       final MyDiscountEvent myDiscountEvent,
                       final MyPromotion myPromotion, final int totalDiscountAmount, final MyBadge myBadge) {
        this.orderMenus = orderMenus;
        this.visitDate = visitDate;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.myDiscountEvent = myDiscountEvent;
        this.myPromotion = myPromotion;
        this.totalDiscountAmount = totalDiscountAmount;
        this.myBadge = myBadge;
    }

}
