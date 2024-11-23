package christmas.service;

import christmas.Domain.Visitor;
import java.util.List;

public class SpecialDiscounter {

    private final List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
    private final boolean state;

    public SpecialDiscounter(Visitor visitor) {
        this.state = isContain(visitor.getWillVisitDate());
    }

    private boolean isContain(int date) {
        return specialDays.contains(date);
    }

    public int calculate() {
        if (state) {
            return 1000;
        }
        return 0;
    }
}
