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
        if (discount > 0 || free > 0) {
            return (discount + free) * -1;
        }
        return 0;
    }

    public int getTotalPayment() {
        return visitor.getTotalPrice() - discount;
    }
}
