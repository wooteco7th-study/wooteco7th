package christmas.service;

import christmas.Domain.Order;
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

    //menu가 존재하는지 검사
    private void validateMenu(String splitMenu) {
        //메뉴 존재 X -> 예외 처리
    }

    private int validateNumber(String inputCount) {
        return Converter.getNumber(inputCount);
    }

    private void validateCountRange(int splitCount) {
        if (splitCount < 1) {
            throw new IllegalArgumentException();
        }
    }

    public Order create() {
        return new Order(splitMenu, splitCount);
    }
}
