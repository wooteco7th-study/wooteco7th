package christmas.domain.discount;

import christmas.domain.OrderForm;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;

import java.time.LocalDate;
import java.util.List;

import static christmas.domain.MenuCategory.MAIN;

public class WeekendStrategy implements DiscountStrategy {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int FRIDAY_VALUE = 5;
    private static final int SATURDAY_VALUE = 6;
    private static final int PER_DISCOUNT_AMOUNT = 2023;

    @Override
    public boolean isApplicable(final VisitDate visitDate) {
        LocalDate localDate = LocalDate.of(YEAR, MONTH, visitDate.getDate());
        int value = localDate.getDayOfWeek().getValue();
        return value == FRIDAY_VALUE || value == SATURDAY_VALUE;
    }

    @Override
    public int appliedAmount(final OrderForm orderForm) {
        List<OrderMenu> dessertMenu = orderForm.findMenuByCategory(MAIN);
        return PER_DISCOUNT_AMOUNT * dessertMenu.size();
    }
}
