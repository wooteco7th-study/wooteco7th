package christmas.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventPlanner {

    public final List<Discount> discounts;

    public EventPlanner(final List<Discount> discounts) {
        this.discounts = discounts;
    }

    public int calculateTotalDiscount() {
        return discounts.stream()
                .filter(Discount::canReceiveDiscount)
                .mapToInt(Discount::calculateDiscount)
                .sum();
    }

    public String getBenefitMenuName() {
        return discounts.stream()
                .filter(Benefit.class::isInstance)
                .filter(Discount::canReceiveDiscount)
                .map(discount -> ((BenefitEvent) discount).getBenefitMenuName())
                .findAny()
                .orElse(Menu.NONE.getName());
    }

    public int getBenefitMenuQuantity() {
        return  discounts.stream()
                .filter(Benefit.class::isInstance)
                .filter(Discount::canReceiveDiscount)
                .map(discount -> ((BenefitEvent) discount).getBenefitMenuQuantity())
                .findAny()
                .orElse(0);
    }

    public Map<DiscountType, Integer> getDiscountResult() {
        return discounts.stream()
                .filter(Discount::canReceiveDiscount)
                .collect(Collectors.toMap(
                        Discount::getDiscountType,
                        Discount::calculateDiscount
                ));
    }

    public String getBadgeName() {
        final int totalDiscount = calculateTotalDiscount();
        return Badge.findByDiscount(totalDiscount).getName();
    }
}
