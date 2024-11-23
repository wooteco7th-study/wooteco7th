package christmas.domain;


import christmas.domain.vo.MyBadge;
import christmas.domain.vo.MyDiscountEvent;
import christmas.domain.vo.Order;
import christmas.domain.vo.VisitDate;

public class Discounting {
    private final Order order;
    private final VisitDate visitDate;
    private final int priceBeforeDiscount;
    private final MyDiscountEvent myDiscountEvent;
    private final int totalDiscountAmount;
    private final MyBadge myBadge;

    public Discounting(final Order order, final VisitDate visitDate, final int priceBeforeDiscount,
                       final MyDiscountEvent myDiscountEvent,
                       final int totalDiscountAmount, final MyBadge myBadge) {
        this.order = order;
        this.visitDate = visitDate;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.myDiscountEvent = myDiscountEvent;
        this.totalDiscountAmount = totalDiscountAmount;
        this.myBadge = myBadge;
    }

}
