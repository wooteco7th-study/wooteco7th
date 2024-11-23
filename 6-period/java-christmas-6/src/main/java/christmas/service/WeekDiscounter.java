package christmas.service;

import christmas.Domain.Visitor;
import java.util.List;

public class WeekDiscounter {

    private static final List<String> WEEKDAY = List.of("SUN", "MON", "TUE", "WED", "TUE");
    private final Visitor visitor;
    private final boolean state;

    public WeekDiscounter(Visitor visitor) {
        this.visitor = visitor;
        this.state = isContain(visitor.getDateName());
    }

    private boolean isContain(String date) {
        return WEEKDAY.contains(date);
    }

    public int calculate() {
        if (state) {
            return visitor.getDessertCount() * 2023;
        }
        return visitor.getMainCount() * 2023;
    }
}
