package christmas.controller;

import christmas.domain.date.Day;
import christmas.domain.event.Badge;
import christmas.domain.event.EventCalculator;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.dto.DiscountDetailsDto;
import christmas.dto.OrderDto;
import christmas.exception.ErrorMessage;
import christmas.exception.ExceptionHandler;
import christmas.service.ChristmasService;
import christmas.util.InputValidator;
import christmas.util.StringParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ChristmasController {

    private static final String REGEX = "([가-힣]+)-([1-9]\\d*)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionHandler exceptionHandler;
    private final ChristmasService christmasService;

    public ChristmasController(final InputView inputView, final OutputView outputView,
                               final ExceptionHandler exceptionHandler,
                               final ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.exceptionHandler = exceptionHandler;
        this.christmasService = christmasService;
    }

    public void process() {
        // 식당 예상 방문 날짜 입력 기능 구현
        Day day = makeDay();
        // 주문 메뉴, 개수 입력 기능 구현
        Orders orders = makeOrders();
        showOrderMenu(day, orders);
        calculateEvent(day, orders);
    }

    private void calculateEvent(final Day day, final Orders orders) {
        EventCalculator eventCalculator = new EventCalculator(day, orders);
        showTotalOrderPrice(eventCalculator);
        showGift(eventCalculator);
        showDiscount(eventCalculator);
    }

    private void showDiscount(final EventCalculator eventCalculator) {
        int christmasDdayDiscount = eventCalculator.calculateChristmasDdayDiscount();
        int dayDiscount = eventCalculator.calculateDayDiscount();
        int specialDiscount = eventCalculator.calculateSpecialDiscount();
        int giftDiscount = eventCalculator.calculateGiftDiscount();
        boolean isWeekday = eventCalculator.isWeekday();
        DiscountDetailsDto dto = new DiscountDetailsDto(christmasDdayDiscount, dayDiscount,
                specialDiscount, giftDiscount, isWeekday);
        outputView.showTitleDiscountDetails(dto);
        calculateResult(eventCalculator, christmasDdayDiscount, dayDiscount, specialDiscount, giftDiscount);
    }

    private void calculateResult(final EventCalculator eventCalculator, final int christmasDdayDiscount,
                           final int dayDiscount, final int specialDiscount, final int giftDiscount) {
        int totalDiscount = christmasDdayDiscount + dayDiscount + specialDiscount;
        int totalEventPrice = totalDiscount + giftDiscount;
        outputView.showTitleTotalDiscount(totalEventPrice);
        showEstimatedPrice(eventCalculator, totalDiscount);
        showBadge(totalEventPrice);
    }

    private void showEstimatedPrice(final EventCalculator eventCalculator, final int totalDiscount) {
        int estimatedPrice = eventCalculator.calculateTotalOrderPrice() - totalDiscount;
        outputView.showTitleEstimatedPrice(estimatedPrice);
    }

    private void showBadge(final int totalEventPrice) {
        Badge badge = Badge.calculateBadge(totalEventPrice);
        outputView.showTitleBadge(badge.name());
    }

    private void showGift(final EventCalculator eventCalculator) {
        Map<Menu, Integer> gifts = eventCalculator.calculateGift();
        outputView.showTitleGiftMenu(OrderDto.from(gifts));
    }

    private void showTotalOrderPrice(final EventCalculator eventCalculator) {
        int totalPrice = eventCalculator.calculateTotalOrderPrice();
        outputView.showTitleTotalOrderPrice(totalPrice);
    }

    private void showOrderMenu(final Day day, final Orders orders) {
        outputView.showInformTitleDiscount(day.getToday());
        outputView.showTitleOrderMenu(OrderDto.from(orders));
    }

    private Orders makeOrders() {
        outputView.showRequestOrder();
        return exceptionHandler.retryUntilSuccess(() -> {
            List<String> inputs = inputView.readOrder();
            return new Orders(inputs.stream()
                    .map(this::makeOrder)
                    .toList());
        });
    }

    private Order makeOrder(final String order) {
        InputValidator.validatePattern(order, PATTERN, ErrorMessage.INVALID_ORDER);
        List<String> tokens = StringParser.findMatchingGroups(order, PATTERN);
        Menu menu = Menu.from(tokens.get(0));
        int quantity = StringParser.parseToInteger(tokens.get(1), ErrorMessage.INVALID_ORDER);
        return new Order(menu, quantity);
    }

    private Day makeDay() {
        outputView.showRequestVisitDay();
        return exceptionHandler.retryUntilSuccess(() -> new Day(inputView.readVisitDay()));
    }

}
