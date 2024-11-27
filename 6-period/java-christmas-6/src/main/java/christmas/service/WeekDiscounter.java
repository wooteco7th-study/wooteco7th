package christmas.service;

import christmas.Domain.Visitor;
import java.util.List;

public class WeekDiscounter {

    private static final List<String> WEEKDAY = List.of("일", "월", "화", "수", "목");
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
        return calculateWeekDay() + calculateWeekEnd();
    }

    private int calculateWeekDay() {
        if (state) {
            return visitor.getDessertCount() * 2023;
        }
        return 0;
    }

    private int calculateWeekEnd() {
        if (state) {
            return 0;
        }
        return visitor.getMainCount() * 2023;
    }

    public String getMessage() {
        if (state) {
            return String.format("평일 할인: -%,d원\n", calculateWeekDay());
        }
        return String.format("주말 할인: -%,d원\n", calculateWeekEnd());
    }
}
