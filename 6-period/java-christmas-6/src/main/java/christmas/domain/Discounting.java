package christmas.domain;


import christmas.domain.vo.MyBadge;
import christmas.domain.vo.MyDiscountResult;
import christmas.domain.vo.MyPromotion;
import christmas.domain.vo.OrderMenus;
import christmas.domain.vo.VisitDate;

public class Discounting {
    private final OrderMenus orderMenus;
    private final VisitDate visitDate;
    private final int priceBeforeDiscount;
    private final MyDiscountResult myDiscountResult;
    private final MyPromotion myPromotion;
    private final int totalDiscountAmount;
    private final MyBadge myBadge;

    public Discounting(final OrderMenus orderMenus, final VisitDate visitDate, final int priceBeforeDiscount,
                       final MyDiscountResult myDiscountResult,
                       final MyPromotion myPromotion, final int totalDiscountAmount, final MyBadge myBadge) {
        this.orderMenus = orderMenus;
        this.visitDate = visitDate;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.myDiscountResult = myDiscountResult;
        this.myPromotion = myPromotion;
        this.totalDiscountAmount = totalDiscountAmount;
        this.myBadge = myBadge;
    }

    public int getFinalPrice() {
        return priceBeforeDiscount - totalDiscountAmount;
    }

    public Discounting updateDiscountResults(MyDiscountResult myDiscountResult) {
        return new Discounting(
                this.orderMenus,
                this.visitDate,
                this.priceBeforeDiscount,
                myDiscountResult,
                this.myPromotion,
                this.totalDiscountAmount,
                this.myBadge
        );
    }

    public Discounting updateMyPromotion(MyPromotion myPromotion) {
        return new Discounting(
                this.orderMenus,
                this.visitDate,
                this.priceBeforeDiscount,
                this.myDiscountResult,
                myPromotion,
                this.totalDiscountAmount,
                this.myBadge
        );
    }

    public Discounting updateTotalDiscountAmount(int totalDiscountAmount) {
        return new Discounting(
                this.orderMenus,
                this.visitDate,
                this.priceBeforeDiscount,
                this.myDiscountResult,
                this.myPromotion,
                totalDiscountAmount,
                new MyBadge(Badge.of(totalDiscountAmount))
        );
    }

    public OrderMenus getOrderMenus() {
        return orderMenus;
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public int getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public MyDiscountResult getMyDiscountEvent() {
        return myDiscountResult;
    }

    public MyPromotion getMyPromotion() {
        return myPromotion;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public MyBadge getMyBadge() {
        return myBadge;
    }
}
