package christmas.domain.event;

import christmas.domain.date.Day;
import christmas.domain.menu.Menu;
import christmas.domain.order.Orders;
import java.util.Collections;
import java.util.Map;

public class EventCalculator {

    private static final Map<Menu, Integer> GIFT = Map.of(Menu.샴페인, 1);
    private static final int GIFT_THRESHOLD = 120_000;

    private final Day today;
    private final Orders orders;
    private final Map<Menu, Integer> gifts;

    public EventCalculator(final Day today, final Orders orders) {
        this.today = today;
        this.orders = orders;
        gifts = calculateGift();
    }

    public int calculateTotalOrderPrice() {
        return orders.calculateTotalPrice();
    }

    public Map<Menu, Integer> calculateGift() {
        if (checkGiftCondition()) {
            return GIFT;
        }
        return Collections.emptyMap();
    }

    public int calculateGiftDiscount() {
        return gifts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public int calculateChristmasDdayDiscount() {
        ChristmasDdayDiscount christmasDdayDiscount = new ChristmasDdayDiscount(today, orders);
        return christmasDdayDiscount.calculateDiscount();
    }

    public int calculateDayDiscount() {
        DayDiscount dayDiscount = new DayDiscount(today, orders);
        return dayDiscount.calculateDiscount();
    }

    public int calculateSpecialDiscount() {
        SpecialDiscount specialDiscount = new SpecialDiscount(today, orders);
        return specialDiscount.calculateDiscount();
    }

    private boolean checkGiftCondition() {
        return today.isInDecember() && calculateTotalOrderPrice() > GIFT_THRESHOLD;
    }

    public boolean isWeekday() {
        return today.isWeekday();
    }
}
