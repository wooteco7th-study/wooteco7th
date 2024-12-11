package christmas.service;

import christmas.domain.discount.BenefitEvent;
import christmas.domain.discount.ChristmasDiscount;
import christmas.domain.discount.Discount;
import christmas.domain.discount.DiscountType;
import christmas.domain.discount.EventPlanner;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderGroup;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.visit.VisitDate;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.dto.BenefitMenuReceipt;
import christmas.dto.DiscountReceipt;
import christmas.dto.OrderMenuReceipt;
import christmas.error.ErrorMessage;
import christmas.util.StringParser;
import christmas.util.StringValidator;
import java.util.List;
import java.util.Map;

public class ChristmasService {

    private static final String SPACE = " ";
    private static final String HYPHEN = "-";
    private static final int MENU_NAME_INDEX = 0;
    private static final int MENU_QUANTITY_INDEX = 1;


    public List<Order> generateOrders(final List<String> orderMenus) {
        validateOrderMenuFormat(orderMenus);
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

    private void validateOrderMenuFormat(final List<String> orderMenus) {
        for (String orderMenu : orderMenus) {
            StringValidator.validateFormat(orderMenu, ErrorMessage.INVALID_WRONG_ORDER_FORMAT);
        }
    }

}
