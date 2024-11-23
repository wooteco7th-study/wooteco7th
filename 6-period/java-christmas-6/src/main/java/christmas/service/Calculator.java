package christmas.service;

import christmas.Domain.Visitor;

public class Calculator {

    private final Visitor visitor;
    private final int discount;

    public Calculator(Visitor visitor, int discount) {
        this.visitor = visitor;
        this.discount = discount;
    }

    public int getTotalDiscount(int free) {
        return Math.max(discount + free, 0) * -1;
    }

    public int getTotalDiscountToBadge(int free) {
        return Math.max(discount + free, 0);
    }

    public int getTotalPayment() {
        return visitor.getTotalPrice() - discount;
    }
}
