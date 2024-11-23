package christmas.service;

import christmas.Domain.Menu;
import christmas.Domain.Visitor;

public class FreeDiscounter {

    private static final String FREE = "샴페인";
    private static final int COUNT = 1;
    private final boolean state;
    private final int freePrice = Menu.getPrice(FREE);

    public FreeDiscounter(Visitor visitor) {
        this.state = isContain(visitor.getTotalPrice());
    }

    private boolean isContain(int price) {
        return price >= 120000;
    }

    public int calculate() {
        if (state) {
            return freePrice;
        }
        return 0;
    }

    public String getMessage() {
        return String.format("증정 이벤트: -%,d원", calculate());
    }

    @Override
    public String toString() {
        if (state) {
            return FREE + " " + COUNT + "개";
        }
        return "없음";
    }
}
