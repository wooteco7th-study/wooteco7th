package christmas.domain;

import christmas.error.ErrorMessage;
import christmas.util.NumberValidator;
import java.util.Objects;

public class OrderMenu {

    private static final int MIN_QUANTITY = 0;

    private final Menu menu;
    private final int quantity;

    private OrderMenu(final Menu menu, final int quantity) {
        NumberValidator.validateRange(quantity, MIN_QUANTITY, Integer.MAX_VALUE, ErrorMessage.WRONG_ORDER_FORMAT);
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderMenu of(final String menuName, final int quantity) {
        return new OrderMenu(
                Menu.findByName(menuName),
                quantity
        );
    }

    public int calculatePrice() {
        return menu.getPrice() * quantity;
    }

    public boolean isDessert() {
        return Objects.equals(menu.getMenuType(), MenuType.DESSERT);
    }

    public boolean isMain() {
        return Objects.equals(menu.getMenuType(), MenuType.MAIN);
    }

    public boolean isDrink() {
        return Objects.equals(menu.getMenuType(), MenuType.DRINK);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
