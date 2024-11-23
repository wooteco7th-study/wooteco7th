package christmas.domain.discount;

import christmas.domain.OrderForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountProcessor {

    private final List<DiscountStrategy> discountStrategies = createDiscountStrategies();

    public Map<String, Integer> process(final OrderForm orderForm) {
        Map<String, Integer> discountList = new HashMap<>();
        for (DiscountStrategy discountStrategy : discountStrategies) {
            if (discountStrategy.isApplicable(orderForm.getOrderDate())) {
                int appliedAmount = discountStrategy.appliedAmount(orderForm);
                discountList.put(discountStrategy.getName(), appliedAmount);
            }
        }
        return discountList;
    }

    private List<DiscountStrategy> createDiscountStrategies() {
        return List.of(
                new ChristmasDDayStrategy(),
                new WeekdayStrategy(),
                new WeekendStrategy(),
                new SpecialStrategy(),
                new FreeGiftStrategy()
        );
    }
}
