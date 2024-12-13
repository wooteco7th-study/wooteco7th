package christmas.domain;

import christmas.error.AppException;
import christmas.error.ErrorMessage;
import christmas.util.StringParser;
import christmas.util.StringValidator;
import java.util.List;

public class OrderMenuGroup {

    private static final String DELIMITER_HYPHEN = "-";
    private static final int MENU_NAME_INDEX = 0;
    private static final int MENU_QUANTITY_INDEX = 1;
    private static final int MAX_QUANTITY = 20;

    private final List<OrderMenu> orderMenus;

    private OrderMenuGroup(final List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public static OrderMenuGroup of(final List<String> inputMenus) {
        validateFormat(inputMenus);
        final List<OrderMenu> orderMenus = inputMenus.stream()
                .map(OrderMenuGroup::createOrderMenu)
                .toList();
        validateQuantity(orderMenus);
        validateOnlyDrink(orderMenus);
        return new OrderMenuGroup(orderMenus);
    }

    public int calculateTotalPrice() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::calculatePrice)
                .sum();
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    private static OrderMenu createOrderMenu(final String inputMenu) {
        final List<String> tokens = StringParser.parseToTokens(inputMenu, DELIMITER_HYPHEN);
        final String menuName = tokens.get(MENU_NAME_INDEX);
        final int menuQuantity = StringParser.parseToNumber(tokens.get(MENU_QUANTITY_INDEX), ErrorMessage.WRONG_ORDER_FORMAT);
        return OrderMenu.of(menuName, menuQuantity);
    }

    private static void validateFormat(final List<String> inputMenus) {
        for (String inputMenu : inputMenus) {
            StringValidator.validateFormat(inputMenu, ErrorMessage.WRONG_ORDER_FORMAT);
        }
    }

    private static void validateQuantity(final List<OrderMenu> orderMenus) {
        if (isExceedsQuantity(orderMenus)) {
            throw new AppException(ErrorMessage.WRONG_ORDER_FORMAT);
        }
    }

    private static void validateOnlyDrink(final List<OrderMenu> orderMenus) {
        if (orderMenus.stream().allMatch(OrderMenu::isDrink)) {
            throw new AppException(ErrorMessage.WRONG_ORDER_FORMAT);
        }
    }

    private static boolean isExceedsQuantity(final List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .mapToInt(OrderMenu::getQuantity)
                .sum() > MAX_QUANTITY;
    }
}
