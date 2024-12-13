package christmas.controller;

import christmas.domain.Day;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.exception.ErrorMessage;
import christmas.exception.ExceptionHandler;
import christmas.service.ChristmasService;
import christmas.util.InputValidator;
import christmas.util.StringParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
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
