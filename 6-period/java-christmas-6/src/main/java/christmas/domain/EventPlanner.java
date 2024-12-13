package christmas.domain;

import christmas.domain.discount.Badge;
import christmas.domain.discount.BenefitDiscount;
import christmas.domain.discount.ChristmasDiscount;
import christmas.domain.discount.Discount;
import christmas.domain.discount.DiscountType;
import christmas.domain.discount.SpecialDayDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventPlanner {

    private final OrderMenuGroup orderMenuGroup;
    private final List<Discount> discounts;

    private EventPlanner(final OrderMenuGroup orderMenuGroup,
                        final List<Discount> discounts) {
        this.orderMenuGroup = orderMenuGroup;
        this.discounts = discounts;
    }

    public static EventPlanner of(final VisitDate visitDate, final OrderMenuGroup orderMenuGroup) {
        final List<Discount> discounts = new ArrayList<>();
        discounts.add(new WeekdayDiscount(DiscountType.WEEKDAY, visitDate, orderMenuGroup));
        discounts.add(new WeekendDiscount(DiscountType.WEEKEND, visitDate, orderMenuGroup));
        discounts.add(new ChristmasDiscount(DiscountType.CHRISTMAS, visitDate, orderMenuGroup));
        discounts.add(new SpecialDayDiscount(DiscountType.SPECIAL, visitDate, orderMenuGroup));
        discounts.add(new BenefitDiscount(DiscountType.BENEFIT, visitDate, orderMenuGroup));
        return new EventPlanner(orderMenuGroup, discounts);
    }

    public int calculateBeforeDiscountTotalPrice() {
        return orderMenuGroup.calculateTotalPrice();
    }

    public int calculateAfterDiscountTotalPrice() {
        return orderMenuGroup.calculateTotalPrice() - calculateTotalDiscount();
    }

    public int calculateTotalDiscount() {
        return discounts.stream()
                .mapToInt(Discount::calculateDiscount)
                .sum();
    }

    public List<Discount> getCanReceiveDiscount() {
        return discounts.stream()
                .filter(discount -> discount.calculateDiscount() > 0)
                .toList();
    }

    public Menu getBenefitMenu() {
        return discounts.stream()
                .filter(discount -> Objects.equals(discount.getDiscountType(), DiscountType.BENEFIT))
                .filter(discount -> discount.calculateDiscount() > 0)
                .findAny()
                .map(discount -> ((BenefitDiscount) discount).getBenefit())
                .orElse(Menu.NONE);
    }

    public int getBenefitQuantity() {
        return discounts.stream()
                .filter(discount -> Objects.equals(discount.getDiscountType(), DiscountType.BENEFIT))
                .filter(discount -> discount.calculateDiscount() > 0)
                .findAny()
                .map(discount -> ((BenefitDiscount) discount).getBenefitQuantity())
                .orElse(0);
    }

    public Badge getBadge() {
        return Badge.findByDiscount(calculateTotalDiscount());
    }
}
