package christmas.service;

import christmas.Domain.Menu;
import christmas.Domain.Order;
import christmas.Exception.InputException;
import java.util.List;

public class OrderCreator {

    private final String splitMenu;
    private final int splitCount;

    public OrderCreator(String order) {
        List<String> splitOrder = Separator.separate(order, "-");

        String splitMenu = splitOrder.get(0);
        validateMenu(splitMenu);
        this.splitMenu = splitMenu;

        int splitCount = validateNumber(splitOrder.get(1));
        validateCountRange(splitCount);
        this.splitCount = splitCount;
    }

    private void validateMenu(String splitMenu) {
        if (!Menu.getMenuName().contains(splitMenu)) {
            throw new InputException();
        }
    }

    private int validateNumber(String inputCount) {
        return Converter.getNumberCount(inputCount);
    }

    private void validateCountRange(int splitCount) {
        if (splitCount < 1) {
            throw new InputException();
        }
    }

    public Order create() {
        return new Order(splitMenu, splitCount);
    }
}
