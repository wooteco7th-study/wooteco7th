package christmas.service;

import christmas.Domain.Visitor;

public class ChristmasDiscounter {

    private final Visitor visitor;
    private final boolean state;

    public ChristmasDiscounter(Visitor visitor) {
        this.visitor = visitor;
        this.state = isContain(visitor.getWillVisitDate());
    }

    private boolean isContain(int date) {
        return 1 <= date && date <= 25;
    }

    public int calculate() {
        if (state) {
            return 1000 + (visitor.getWillVisitDate() - 1) * 100;
        }
        return 0;
    }

    public String getMessage() {
        return String.format("크리스마스 디데이 할인: -%,d원", calculate());
    }
}
