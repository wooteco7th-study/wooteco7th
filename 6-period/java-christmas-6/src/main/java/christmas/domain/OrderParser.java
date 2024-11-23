package christmas.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class OrderParser {

    public static final String ORDER_DELIMITER = ",";
    public static final String MENU_QUANTITY_DELIMITER = "-";

    public static OrderForm parse(int visitDate, String orders) {
        String[] menuAndQuantity = orders.split(ORDER_DELIMITER);
        List<OrderMenu> orderForm = new ArrayList<>();
        Arrays.stream(menuAndQuantity).forEach(order -> addOrder(order, orderForm));
        return new OrderForm(visitDate, orderForm);
    }

    private static void addOrder(final String order, final List<OrderMenu> orderForm) {
        String[] data = order.split(MENU_QUANTITY_DELIMITER);

        IntStream.range(0, data.length)
                .mapToObj(i -> Menu.from(data[0]))
                .forEach(menu -> {
                    Quantity quantity = new Quantity(data[1]);
                    OrderMenu orderMenu = new OrderMenu(menu, quantity);

                    orderForm.add(orderMenu);
                });
    }
}
