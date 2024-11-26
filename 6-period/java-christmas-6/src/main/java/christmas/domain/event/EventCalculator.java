package christmas.domain.event;

import christmas.domain.event.Badge;
import christmas.domain.event.discount.Discount;
import christmas.domain.event.gift.Gift;
import christmas.domain.order.Orders;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class EventCalculator {

    private final List<Discount> discounts;
    private final List<Gift> bonuses;

    public EventCalculator(final List<Discount> discounts, final List<Gift> bonuses) {
        this.discounts = discounts;
        this.bonuses = bonuses;
    }

    public BigDecimal calculateTotalBenefitPrice() {
        return Stream.concat(discounts.stream().map(Discount::calculateAmount),
                        bonuses.stream().map(Gift::calculateAmount))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal sumDiscountPrice() {
        return discounts.stream()
                .map(Discount::calculateAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateEstimatedPrice(final Orders orders) {
        BigDecimal price = orders.calculateTotalPrice();
        return price.subtract(sumDiscountPrice());
    }

    public String getBadgeName() {
        BigDecimal totalBenefitPrice = calculateTotalBenefitPrice();
        return Badge.showName(totalBenefitPrice);
    }
}
