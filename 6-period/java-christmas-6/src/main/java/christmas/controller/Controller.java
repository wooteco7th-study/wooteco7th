package christmas.controller;

import static christmas.exception.ErrorMessage.INVALID_ORDER;

import christmas.domain.Day;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.Quantity;
import christmas.exception.CustomIllegalArgumentException;
import christmas.util.Converter;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private static final String ORDER_REGEX = "([ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+)-(\\d+)";
    private static final Pattern PATTERN = Pattern.compile(ORDER_REGEX);

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void process() {
        outputView.commentWelcomeMessage();
        Day visitDay = createDay();
        Orders orders = createOrders();
    }

    private Orders createOrders() {
        outputView.commentOrderMenu();
        String input = inputView.readLine();
        Orders orders = new Orders(new ArrayList<>());
        for (String order : input.split(",")) {
            validateOrder(orders, order);
        }
        return orders;
    }

    private void validateOrder(final Orders orders, final String order) {
        Matcher matcher = PATTERN.matcher(order);
        if (!matcher.matches()) {
            throw new CustomIllegalArgumentException(INVALID_ORDER.getMessage());
        }
        matcher.reset();
        while (matcher.find()) {
            Menu menu = Menu.from(matcher.group(1));
            Quantity quantity = new Quantity(Converter.convertToInteger(matcher.group(2)));
            orders.add(new Order(menu, quantity));
        }
    }

    private Day createDay() {
        outputView.commentVisitDate();
        String day = inputView.readLine();
        return new Day(Converter.convertToInteger(day));
    }
}
