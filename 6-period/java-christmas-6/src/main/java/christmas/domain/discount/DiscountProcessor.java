package christmas.domain.discount;

import christmas.domain.OrderForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountProcessor {

    private final List<DiscountStrategy> discountStrategies = createDiscountStrategies();

    public Map<DiscountStrategy, Integer> process(final OrderForm orderForm) {
        Map<DiscountStrategy, Integer> discountList = new HashMap<>();
        for (DiscountStrategy discountStrategy : discountStrategies) {
            if (discountStrategy.isApplicable(orderForm.getOrderDate())) {
                int appliedAmount = discountStrategy.appliedAmount(orderForm);
                discountList.put(discountStrategy, appliedAmount);
            }
        }
        return discountList;
//        discountStrategies.stream()
//                .filter(discountStrategy -> discountStrategy.isApplicable(orderForm.getOrderDate()))
//                .map(discountStrategy -> discountStrategy.appliedAmount(orderForm));
    }

    private List<DiscountStrategy> createDiscountStrategies() {
        return List.of(
                new ChristmasDDayStrategy(),
                new WeekdayStrategy(),
                new WeekendStrategy(),
                new SpecialStrategy()
        );
    }
}
