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
                putDiscount(orderForm, discountStrategy, discountList);
            }
        }
        return discountList;
    }

    private static void putDiscount(final OrderForm orderForm, final DiscountStrategy discountStrategy, final Map<String, Integer> discountList) {
        int appliedAmount = discountStrategy.appliedAmount(orderForm);
        if (appliedAmount > 0) {
            discountList.put(discountStrategy.getName(), appliedAmount);
        }
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
