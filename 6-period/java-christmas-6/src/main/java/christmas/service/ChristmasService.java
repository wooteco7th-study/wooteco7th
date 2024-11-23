package christmas.service;

import christmas.domain.BenefitEvent;
import christmas.domain.ChristmasDiscount;
import christmas.domain.Discount;
import christmas.domain.DiscountType;
import christmas.domain.EventPlanner;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderGroup;
import christmas.domain.SpecialDiscount;
import christmas.domain.VisitDate;
import christmas.domain.WeekdayDiscount;
import christmas.domain.WeekendDiscount;
import christmas.dto.BenefitMenuReceipt;
import christmas.dto.DiscountReceipt;
import christmas.dto.OrderMenuReceipt;
import christmas.error.ErrorMessage;
import christmas.util.StringParser;
import java.util.List;
import java.util.Map;

public class ChristmasService {

    private static final String SPACE = " ";
    private static final String HYPHEN = "-";
    private static final int MENU_NAME_INDEX = 0;
    private static final int MENU_QUANTITY_INDEX = 1;


    public List<Order> generateOrders(final List<String> orderMenus) {
        return orderMenus.stream()
                .map(this::createOrder)
                .toList();
    }

    public List<OrderMenuReceipt> generateOrderMenuReceipts(final List<Order> orders) {
        return orders.stream()
                .map(OrderMenuReceipt::of)
                .toList();
    }

    public List<Discount> generateDiscounts(final OrderGroup orderGroup, final VisitDate visitDate) {
        return List.of(
                new WeekdayDiscount(DiscountType.WEEKDAY_DISCOUNT, orderGroup, visitDate),
                new WeekendDiscount(DiscountType.WEEKEND_DISCOUNT, orderGroup, visitDate),
                new SpecialDiscount(DiscountType.SPECIAL_DISCOUNT, orderGroup, visitDate),
                new ChristmasDiscount(DiscountType.CHRISTMAS_DISCOUNT, orderGroup, visitDate),
                new BenefitEvent(DiscountType.BENEFIT_EVENT, orderGroup)
        );
    }

    public List<DiscountReceipt> generateDiscountReceipts(final EventPlanner eventPlanner) {
        final Map<DiscountType, Integer> discountResult = eventPlanner.getDiscountResult();
        return discountResult.entrySet().stream()
                .map(DiscountReceipt::of)
                .toList();
    }

    public BenefitMenuReceipt generateBenefitMenuReceipt(final EventPlanner eventPlanner) {
        return new BenefitMenuReceipt(eventPlanner.getBenefitMenuName(), eventPlanner.getBenefitMenuQuantity());
    }

    private Order createOrder(final String input) {
        final String removedPattern = StringParser.removePattern(input, SPACE);
        final List<String> tokens = StringParser.parseToTokens(removedPattern, HYPHEN);
        final Menu menu = Menu.findByName(tokens.get(MENU_NAME_INDEX));
        final int quantity = StringParser.parseToInt(tokens.get(MENU_QUANTITY_INDEX), ErrorMessage.INVALID_WRONG_ORDER_FORMAT);
        return new Order(menu, quantity);
    }

}
