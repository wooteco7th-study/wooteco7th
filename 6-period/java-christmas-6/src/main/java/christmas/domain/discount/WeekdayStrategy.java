package christmas.domain.discount;

import christmas.domain.OrderForm;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;

import java.time.LocalDate;
import java.util.List;

import static christmas.domain.MenuCategory.DESSERT;
import static christmas.domain.discount.DiscountCategory.WEEKDAY;

public class WeekdayStrategy implements DiscountStrategy {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int FRIDAY_VALUE = 5;
    private static final int SATURDAY_VALUE = 6;
    private static final int PER_DISCOUNT_AMOUNT = 2023;

    @Override
    public boolean isApplicable(final VisitDate visitDate) {
        LocalDate localDate = LocalDate.of(YEAR, MONTH, visitDate.getDate());
        int value = localDate.getDayOfWeek().getValue();
        return value != FRIDAY_VALUE && value != SATURDAY_VALUE;
    }

    @Override
    public int appliedAmount(final OrderForm orderForm) {
        List<OrderMenu> dessertMenu = orderForm.findMenuByCategory(DESSERT);
        return dessertMenu.stream()
                .map(menu -> menu.getQuantity() * PER_DISCOUNT_AMOUNT)
                .reduce(0, Integer::sum);
    }

    @Override
    public String getName() {
        return WEEKDAY.getName();
    }
}
