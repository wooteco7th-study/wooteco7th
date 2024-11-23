package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.error.ErrorMessage;
import christmas.util.StringParser;
import java.util.List;

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

    private Order createOrder(final String input) {
        final String removedPattern = StringParser.removePattern(input, SPACE);
        final List<String> tokens = StringParser.parseToTokens(removedPattern, HYPHEN);
        final Menu menu = Menu.findByName(tokens.get(MENU_NAME_INDEX));
        final int quantity = StringParser.parseToInt(tokens.get(MENU_QUANTITY_INDEX), ErrorMessage.INVALID_WRONG_ORDER_FORMAT);
        return new Order(menu, quantity);
    }

}
